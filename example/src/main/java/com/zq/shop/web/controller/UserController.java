package com.zq.shop.web.controller;

import com.zq.app.app.social.AppSignUpUtils;
import com.zq.core.restful.ServerResponse;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午7:24
 * @Package com.zq.shop.web
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @PostMapping("/register")
    public ServerResponse customRegister(String userId, String providerId, String openId) {
        appSignUpUtils.doPostSignUp(userId, providerId, openId);
        return ServerResponse.createBySuccess("注册成功");
    }

    @Getter
    public ServerResponse getUser() {

        return ServerResponse.createBySuccess("用户");
    }
}
