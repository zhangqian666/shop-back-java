package com.zq.shop.authtication;

import com.zq.core.authorize.AuthorizeConfigProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午7:28
 * @Package com.zq.shop.authtication
 **/

@Component
public class ExampleAuthticaitionConfigProvider implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.antMatchers(HttpMethod.POST, "/user/register")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/fonts/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs"
                        , "/swagger-resources/configuration/ui"
                        , "/swagger-resources"
                        , "/swagger-resources/configuration/security"
                        , "/swagger-ui.html"
                        , "/docs.html")
                .permitAll();

        return false;
    }
}
