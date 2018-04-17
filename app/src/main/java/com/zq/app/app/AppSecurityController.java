package com.zq.app.app;

import com.zq.app.app.social.AppSignUpUtils;
import com.zq.core.properties.SecurityConstants;
import com.zq.core.restful.ServerResponse;
import com.zq.core.social.SocialController;
import com.zq.core.social.qq.connect.QQConnectionFactory;
import com.zq.core.social.support.SocialUserInfo;
import com.zq.core.social.wexin.connect.WeXinAccessGrant;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午12:51
 * @Package com.zq.app.app
 **/

@RestController
public class AppSecurityController extends SocialController {


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Autowired
    ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    UsersConnectionRepository usersConnectionRepository;

    /**
     * 需要注册时跳到这里，返回401和用户信息给前端 只有code 上传验证的时候才能回调这个方法
     *
     * @param request
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        appSignUpUtils.saveConnectionData(connection.createData());
        return buildSocialUserInfo(connection);
    }

}
