package com.zq.shop.web.controller.NormalUser;

import com.zq.app.app.social.AppSignUpUtils;
import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.service.IFileService;
import com.zq.shop.web.service.IShopUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午7:24
 * @Package com.zq.shop.web
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 * @ApiResponse：HTTP响应其中1个描述
 * @ApiResponses：HTTP响应整体描述
 * @ApiIgnore：使用该注解忽略这个API
 * @ApiError ：发生错误返回的信息
 * @ApiImplicitParam：一个请求参数
 * @ApiImplicitParams：多个请求参数
 **/
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Autowired
    private IShopUserService iShopUserService;

    @Autowired
    private IFileService iFileService;


    @ApiOperation("注册账号")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户id", required = true)
            , @ApiImplicitParam(name = "providerId", value = "第三方id", required = true)})
    @PostMapping("/register")
    public ServerResponse register(ShopUser shopUser, String providerId, String openId) {
        ServerResponse register = iShopUserService.register(shopUser);
        if (register.isSuccess()) {
            ShopUser data = (ShopUser) register.getData();
            if (StringUtils.isNoneBlank(openId)) {
                appSignUpUtils.doPostSignUp(data.getUid().toString(), providerId, openId);
            }
            data.setPassword("");
            return ServerResponse.createBySuccess("注册成功", data);
        }
        return register;
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/info")
    public ServerResponse getUser(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer userId) {
        if (userId == null) {
            return iShopUserService.getUserInfo(defaultUserDetails.getUsername());
        } else {
            return iShopUserService.getUserInfo(userId);
        }

    }

    @ApiOperation("更新用户头像")
    @PostMapping("/update/image")
    public ServerResponse updateUserImage(@RequestParam("upload_file") MultipartFile file,
                                          @AuthenticationPrincipal DefaultUserDetails defaultUserDetails,
                                          HttpServletRequest httpServletRequest) {
        String path = httpServletRequest.getSession().getServletContext().getRealPath("upload");
        String uploadFile = iFileService.uploadFile(file, path);
        return iShopUserService.updateUserImage(uploadFile, defaultUserDetails.getUid());
    }

    @ApiOperation("修改密码")
    @PostMapping("/update/password")
    public ServerResponse updateUserPassword(
            @AuthenticationPrincipal DefaultUserDetails defaultUserDetails, String password) {
        return iShopUserService.updateUserPassword(password, defaultUserDetails.getUid());
    }

}
