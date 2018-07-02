package com.zq.shop.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.zq.core.restful.ResponseCode;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Product;
import com.zq.shop.web.bean.ProductCategory;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.ProductCategoryMapper;
import com.zq.shop.web.mappers.ProductMapper;
import com.zq.shop.web.mappers.ShopUserMapper;
import com.zq.shop.web.service.IProductCategoryService;
import com.zq.shop.web.service.IProductService;
import com.zq.shop.web.vo.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午2:05
 * @Package com.zq.shop.web.service.impl
 **/


@Service(value = "iProductService")
public class ProductServiceImpl implements IProductService {


    @Autowired
    private IDMapper idMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private IProductCategoryService iProductCategoryService;

    @Autowired
    private ShopUserMapper shopUserMapper;


    @Override
    public ServerResponse<List<ProductVo>> getProductListByKeywordCategory(String keyword, Integer categoryId, int pagenum, int pagesize, String orderby) {

        if (StringUtils.isBlank(keyword) && categoryId == null) {
            //排序实现: 数据库字段 + " desc" 或 数据库字段 + " asc"
            PageHelper.startPage(0, 10, "id desc");
            List<Product> products = productMapper.selectByNameAndCategoryIdsOnSale(null, null);
            return ServerResponse.createBySuccess(assembleProductVos(products));
        }

        List<Integer> categoryIdList = null;
        if (categoryId != null) {
            ProductCategory category = productCategoryMapper.selectByPrimaryKey(categoryId);
            if (category == null && StringUtils.isBlank(keyword)) {
                return ServerResponse.createBySuccess();
            }
            categoryIdList = iProductCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }

        if (StringUtils.isNotBlank(keyword)) {
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        //排序实现: 数据库字段 + " desc" 或 数据库字段 + " asc"
        PageHelper.startPage(0, 10, "id desc");
        List<Product> products = productMapper.selectByNameAndCategoryIdsOnSale(keyword, categoryIdList);
        return ServerResponse.createBySuccess(assembleProductVos(products));

    }

    @Override
    public ServerResponse<ProductVo> details(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        if (product.getStatus() != Const.ProductStatus.ON_SALE) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }

        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        ShopUser shopUser = shopUserMapper.selectByPrimaryKey(productVo.getUserId());
        productVo.setUsername(shopUser.getUsername());
        return ServerResponse.createBySuccess(productVo);
    }

    @Override
    public ServerResponse<ProductVo> saveOrUpdateProduct(Product product, Integer userId) {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0) {
                    product.setMainImage(subImageArray[0]);
                }
            }

            if (product.getId() != null) {
                ShopUser shopUser = shopUserMapper.selectByPrimaryKey(userId);
                if (product.getUserId().intValue() == userId.intValue() || shopUser.getRole() == 0 || shopUser.getRole() == 1) {
                    int rowCount = productMapper.updateByPrimaryKey(product);
                    if (rowCount > 0) {
                        return ServerResponse.createBySuccessMessage("更新产品成功");
                    }
                    return ServerResponse.createBySuccessMessage("更新产品失败");
                } else {
                    return ServerResponse.createBySuccessMessage("您没有权限更改该产品信息");
                }

            } else {
                product.setUserId(userId);
                product.setId(idMapper.findId(Const.IDType.PRODUCT_ID));
                int rowCount = productMapper.insert(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccessMessage("新增产品成功");
                }
                return ServerResponse.createBySuccessMessage("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");

    }

    @Override
    public ServerResponse<List<ProductVo>> getProductList(Integer uid, int pageNum, int pageSize) {
        ShopUser shopUser = shopUserMapper.selectByPrimaryKey(uid);
        List<Product> products;
        if (shopUser.getRole() == 0 || shopUser.getRole() == 1) {
            products = productMapper.find();
        } else {
            products = productMapper.findByUserId(uid);
        }
        return ServerResponse.createBySuccess(assembleProductVos(products));
    }

    @Override
    public ServerResponse<List<ProductVo>> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        List<Product> products = productMapper.findByNameLikeOrIdOnSale(productName, productId);
        return ServerResponse.createBySuccess(assembleProductVos(products));
    }


    private List<ProductVo> assembleProductVos(List<Product> list) {
        List<ProductVo> productVos = Lists.newArrayList();
        for (Product product : list) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            ShopUser shopUser = shopUserMapper.selectByPrimaryKey(productVo.getUserId());
            productVo.setUsername(shopUser.getUsername());
            productVos.add(productVo);
        }
        return productVos;
    }

}
