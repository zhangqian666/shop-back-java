package com.zq.shop.web.controller.NormalUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @ApiOperation("生成订单")
    @PostMapping("/create")
    public ServerResponse createOrder(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer shippingId) {
        return iOrderService.createOrder(defaultUserDetails.getUid(), shippingId);
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public ServerResponse cancelOrder(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Long orderId) {
        return iOrderService.cancel(defaultUserDetails.getUid(), orderId);
    }

    @ApiOperation("订单详情")
    @PostMapping("/details")
    public ServerResponse orderDetails(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Long orderId) {
        return iOrderService.getOrderDetail(defaultUserDetails.getUid(), orderId);
    }

    @ApiOperation("订单列表")
    @PostMapping("/list")
    public ServerResponse list(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails) {
        return iOrderService.getOrderList(defaultUserDetails.getUid(), 0, 0);
    }

    @ApiOperation("已选商品列表")
    @PostMapping("/list/checked")
    public ServerResponse listChecked(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails) {
        return iOrderService.getOrderCheckedProductList(defaultUserDetails.getUid(), 0, 0);
    }


}
