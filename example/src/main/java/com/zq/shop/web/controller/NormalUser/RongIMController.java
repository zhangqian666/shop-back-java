package com.zq.shop.web.controller.NormalUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.IRongIMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/30 上午11:49
 * @Package com.zq.shop.web.controller.NormalUser
 **/

@Slf4j
@Api(tags = "融云接口管理")
@RestController
@RequestMapping("/rongim")
public class RongIMController {

    @Autowired
    IRongIMService iRongIMService;

    @ApiOperation("获取token")
    @GetMapping("/token")
    public ServerResponse getUser(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails) {
        return iRongIMService.getToken(defaultUserDetails.getUid());
    }

}
