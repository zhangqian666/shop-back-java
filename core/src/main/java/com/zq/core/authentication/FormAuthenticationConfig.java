package com.zq.core.authentication;

import com.zq.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午2:59
 * @Package com.zq.core.authentication
 **/


@Component
public class FormAuthenticationConfig {

    @Autowired
    private AuthenticationFailureHandler zqAuthenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler zqAuthenticationSuccessHandler;

    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(zqAuthenticationSuccessHandler)
                .failureHandler(zqAuthenticationFailureHandler);

    }
}
