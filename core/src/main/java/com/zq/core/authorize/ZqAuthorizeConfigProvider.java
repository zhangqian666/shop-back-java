package com.zq.core.authorize;

import com.zq.core.properties.SecurityConstants;
import com.zq.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 *
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午2:04
 * @Package com.zq.core.authorize
 **/

@Component
@Order(Integer.MIN_VALUE)
public class ZqAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getOauth2Properties().getLoginUrl(),
                securityProperties.getSocialProperties().getFilterProcessesUrl() + "/*",
                securityProperties.getBrowserProperties().getSignInPage(),
                securityProperties.getBrowserProperties().getSignUpUrl(),
                securityProperties.getBrowserProperties().getSession().getSessionInvalidUrl()).permitAll();

        if (StringUtils.isNotBlank(securityProperties.getBrowserProperties().getSignOutUrl())) {
            config.antMatchers(securityProperties.getBrowserProperties().getSignOutUrl()).permitAll();
        }
        return false;
    }
}
