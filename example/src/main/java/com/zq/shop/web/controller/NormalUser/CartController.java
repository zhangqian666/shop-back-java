package com.zq.shop.web.controller.NormalUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午9:02
 * @Package com.zq.shop.web.controller
 **/

@Api(tags = "购物车")
@RestController
@RequestMapping("/user/cart")
public class CartController {


    @Autowired
    private ICartService iCartService;

    @ApiOperation("获取购物车商品列表")
    @GetMapping("/list")
    public ServerResponse list(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails) {
        return iCartService.list(defaultUserDetails.getUid());
    }

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ServerResponse addCart(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails
            , Integer productId, Integer count) {
        return iCartService.add(defaultUserDetails.getUid(), productId, count);
    }

    @ApiOperation("删除商品")
    @PostMapping("/delete")
    public ServerResponse deleteCart(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, String productIds) {

        return iCartService.delete(defaultUserDetails.getUid(), productIds);
    }

    @ApiOperation("勾选商品")
    @PostMapping("/select")
    public ServerResponse select(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, @ApiParam("不传，代表全部商品") Integer productId, @ApiParam(value = "是否选择 是 0，否 1") Integer checked) {
        return iCartService.selectOrUnSelect(defaultUserDetails.getUid(), productId, checked);
    }

    @ApiOperation("更改购物车商品数量")
    @PostMapping("/update/count")
    public ServerResponse updateCount(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer productId, Integer count) {

        return iCartService.updateCount(defaultUserDetails.getUid(), productId, count);
    }


}
