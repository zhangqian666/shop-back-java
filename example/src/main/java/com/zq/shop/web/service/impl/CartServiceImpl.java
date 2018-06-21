package com.zq.shop.web.service.impl;

import com.google.common.base.Splitter;
import com.zq.core.restful.ResponseCode;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Cart;
import com.zq.shop.web.bean.Product;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.CartMapper;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.ProductMapper;
import com.zq.shop.web.service.ICartService;
import com.zq.shop.web.service.IProductService;
import com.zq.shop.web.vo.CartVo;
import com.zq.shop.web.vo.ProductVo;
import com.zq.shop.web.vo.CartShopVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午5:42
 * @Package com.zq.shop.web.service.impl
 **/

@Slf4j
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IDMapper idMapper;

    @Autowired
    private IProductService iProductService;


    public ServerResponse add(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        if (productMapper.selectByPrimaryKey(productId) == null) {
            return ServerResponse.createByErrorMessage("无此产品");
        }


        Cart cart = cartMapper.findOneByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //这个产品不在这个购物车里,需要新增一个这个产品的记录
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setProductId(productId);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);
        } else {
            //这个产品已经在购物车里了.
            //如果产品已存在,数量相加
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return ServerResponse.createBySuccessMessage(String.format("添加产品：%d成功", productId));
    }

    public ServerResponse updateCount(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.findOneByUserIdAndProductId(userId, productId);
        if (cart != null) {
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        CartVo cartVo = new CartVo();
        BeanUtils.copyProperties(cart, cartVo);
        return ServerResponse.createBySuccess(cartVo);
    }

    public ServerResponse delete(Integer userId, String productIds) {
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productList)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdAndProductId(userId, productList);
        return ServerResponse.createBySuccessMessage(String.format("删除产品：%s成功", productIds));
    }


    public ServerResponse list(Integer userId) {
        return ServerResponse.createBySuccess(getCarts(userId));
    }

    public ServerResponse selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
        cartMapper.updateCheckedByUserIdAndProductId(checked, userId, productId);
        return ServerResponse.createBySuccess(getCarts(userId));
    }


    /**
     * 获取全部购物车商品列表
     *
     * @param userId
     * @return
     */
    private List<CartShopVo> getCarts(Integer userId) {
        List<Cart> cartList = cartMapper.findByUserId(userId);
        List<CartVo> newCartList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Cart cartItem : cartList) {
                Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
                if (product != null) {
                    //判断库存
                    int buyLimitCount = 0;
                    if (product.getStock() >= cartItem.getQuantity()) {
                        //库存充足的时候
                        buyLimitCount = cartItem.getQuantity();
                    } else {
                        buyLimitCount = product.getStock();
                        if (buyLimitCount == 0) continue;
                        //购物车中更新有效库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cartItem.getId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    cartItem.setQuantity(buyLimitCount);
                }
                CartVo cartVo = new CartVo();
                BeanUtils.copyProperties(cartItem, cartVo);
                ProductVo data = iProductService.details(cartVo.getProductId()).getData();
                cartVo.setProductVo(data);
                newCartList.add(cartVo);

            }
        }

        Map<Integer, List<CartVo>> listMap = new HashMap<>();
        for (CartVo cb : newCartList) {
            if (cb.getProductVo() == null) continue;
            Integer productUserId = cb.getProductVo().getUserId();
            if (listMap.containsKey(productUserId)) {
                listMap.get(productUserId).add(cb);
            } else {
                List<CartVo> cartVos = new ArrayList<>();
                cartVos.add(cb);
                listMap.put(productUserId, cartVos);
            }
        }
        List<CartShopVo> cartShopVos = new ArrayList<>();
        for (Map.Entry<Integer, List<CartVo>> integerListEntry : listMap.entrySet()) {
            CartShopVo cartShopVo = new CartShopVo();
            cartShopVo.setUserId(integerListEntry.getKey());
            cartShopVo.setUsername(integerListEntry.getValue().get(0).getProductVo().getUsername());
            cartShopVo.setCartVos(integerListEntry.getValue());
            cartShopVos.add(cartShopVo);
        }

        return cartShopVos;
    }
}
