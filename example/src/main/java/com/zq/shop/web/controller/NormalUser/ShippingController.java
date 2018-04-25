package com.zq.shop.web.controller.NormalUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Shipping;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.service.IShippingService;
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
 * @Data 2018/4/22 下午9:16
 * @Package com.zq.shop.web.controller.NormalUser
 **/

@Api(tags = "收货地址管理")
@RestController
@RequestMapping("/user/shipping")
public class ShippingController {

    @Autowired
    private IShippingService iShippingService;

    @Autowired
    private IShopUserService iShopUserService;

    @ApiOperation("获取收货地址列表")
    @PostMapping("/list")
    public ServerResponse list(Authentication authentication) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iShippingService.list(userInfo.getData().getUid(), 0, 0);
    }

    @ApiOperation("添加收货地址")
    @PostMapping("/add")
    public ServerResponse add(Authentication authentication, Shipping shipping) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iShippingService.add(userInfo.getData().getUid(), shipping);
    }

    @ApiOperation("删除收货地址")
    @PostMapping("/del")
    public ServerResponse del(Authentication authentication, Integer shippingId) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iShippingService.del(userInfo.getData().getUid(), shippingId);
    }

    @ApiOperation("更新收货地址")
    @PostMapping("/update")
    public ServerResponse update(Authentication authentication, Shipping shipping) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iShippingService.update(userInfo.getData().getUid(), shipping);
    }

    @ApiOperation("选择默认收货地址")
    @PostMapping("/select")
    public ServerResponse<Shipping> select(Authentication authentication, Integer shippingId) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iShippingService.select(userInfo.getData().getUid(), shippingId);
    }

}
