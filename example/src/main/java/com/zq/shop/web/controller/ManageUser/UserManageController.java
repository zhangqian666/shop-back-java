package com.zq.shop.web.controller.ManageUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.IShopUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/7/6 下午1:48
 * @Package com.zq.shop.web.controller.ManageUser
 **/

@Slf4j
@Api(tags = "管理员：用户管理")
@RestController
@RequestMapping("manage/user")
public class UserManageController {

    @Autowired
    IShopUserService iShopUserService;

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public ServerResponse list(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails) {
        return iShopUserService.manageList(defaultUserDetails.getUid());
    }

    @ApiOperation("获取用户列表")
    @PostMapping("/update/role")
    public ServerResponse updateRole(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer userId, Integer role) {

        return iShopUserService.manageUpdateRole(defaultUserDetails.getUid(), userId, role);
    }
}
