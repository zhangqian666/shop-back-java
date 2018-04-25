package com.zq.shop.web.controller.NormalUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.service.IShopUserService;
import com.zq.shop.web.service.IUserFriendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/24 下午3:02
 * @Package com.zq.shop.web.controller.NormalUser
 **/

@Api(tags = "好友管理")
@RestController
@RequestMapping("/user/friends")
public class UserFriendsController {

    @Autowired
    private IUserFriendsService iUserFriendsService;
    @Autowired
    private IShopUserService iShopUserService;

    @ApiOperation("获取好友列表")
    @GetMapping("/list")
    public ServerResponse list(Authentication authentication) {
        ShopUser shopUser = iShopUserService.getUserInfo(authentication.getName()).getData();

        return iUserFriendsService.list(shopUser.getUid());
    }

    @ApiOperation("添加好友")
    @GetMapping("/create")
    public ServerResponse create(Authentication authentication, Integer followId) {
        ShopUser shopUser = iShopUserService.getUserInfo(authentication.getName()).getData();

        return iUserFriendsService.create(shopUser.getUid(), followId);
    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    public ServerResponse delete(Authentication authentication, Integer followId) {
        ShopUser shopUser = iShopUserService.getUserInfo(authentication.getName()).getData();

        return iUserFriendsService.delete(shopUser.getUid(), followId);
    }
}
