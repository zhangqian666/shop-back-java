package com.zq.shop.web.controller.NormalUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.service.IOrderService;
import com.zq.shop.web.service.IShopUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午9:25
 * @Package com.zq.shop.web.controller.NormalUser
 **/

@Api(tags = "订单管理")
@RestController
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IShopUserService iShopUserService;

    @ApiOperation("生成订单")
    @PostMapping("/create")
    public ServerResponse createOrder(Authentication authentication, Integer shippingId) {
        ShopUser data = iShopUserService.getUserInfo(authentication.getName()).getData();
        return iOrderService.createOrder(data.getUid(), shippingId);
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public ServerResponse cancelOrder(Authentication authentication, Long orderId) {
        ShopUser data = iShopUserService.getUserInfo(authentication.getName()).getData();
        return iOrderService.cancel(data.getUid(), orderId);
    }

    @ApiOperation("订单详情")
    @PostMapping("/details")
    public ServerResponse orderDetails(Authentication authentication, Long orderId) {
        ShopUser data = iShopUserService.getUserInfo(authentication.getName()).getData();
        return iOrderService.getOrderDetail(data.getUid(), orderId);
    }

    @ApiOperation("订单列表")
    @PostMapping("/list")
    public ServerResponse list(Authentication authentication) {
        ShopUser data = iShopUserService.getUserInfo(authentication.getName()).getData();
        return iOrderService.getOrderList(data.getUid(), 0, 0);
    }

    @ApiOperation("已选商品列表")
    @PostMapping("/list/checked")
    public ServerResponse listChecked(Authentication authentication) {
        ShopUser data = iShopUserService.getUserInfo(authentication.getName()).getData();
        return iOrderService.getOrderCheckedProductList(data.getUid(), 0, 0);
    }




}
