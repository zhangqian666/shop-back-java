package com.zq.app.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zq.app.app.authentication.openid.OpenIdAuthenticationConfig;
import com.zq.core.authentication.FormAuthenticationConfig;
import com.zq.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zq.core.authorize.AuthorizeConfigManager;
import com.zq.core.restful.ServerResponse;
import com.zq.core.validate.code.ValidateCodeSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午3:36
 * @Package com.zq.app.server
 **/

/**
 * 资源服务器配置
 */

@Slf4j
@Configuration
@EnableResourceServer
public class ZqResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private OpenIdAuthenticationConfig openIdAuthenticationConfig;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .apply(openIdAuthenticationConfig)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authenticationEntryPoint());
    }

    /**
     * 未登录 返回401
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            log.error("资源服务器认证错误");
            e.printStackTrace();
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(
                    objectMapper.writeValueAsString(
                            ServerResponse.createByErrorCodeMessage(
                                    HttpStatus.UNAUTHORIZED.value(),
                                    "账号未登录"
                            )));
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        };
    }

}
