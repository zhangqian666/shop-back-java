package com.zq.core.social;

import com.zq.core.properties.SecurityProperties;
import com.zq.core.social.support.SocialAuthenticationFilterPostProcessor;
import com.zq.core.social.support.ZqSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午7:54
 * @Package com.zq.core.social
 **/

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private SecurityProperties securityProperties;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;


    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("zq_");
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    /**
     * 社交登录配置类，供浏览器或app模块引入设计登录配置用。
     *
     * @return
     */
    @Bean
    public SpringSocialConfigurer zqSocialSecurityConfig() {
        String filterProcessesUrl = securityProperties.getSocialProperties().getFilterProcessesUrl();
        ZqSpringSocialConfigurer configurer = new ZqSpringSocialConfigurer(filterProcessesUrl);
        configurer.signupUrl(securityProperties.getBrowserProperties().getSignUpUrl());
        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return configurer;
    }

    /**
     * 用来处理注册流程的工具类
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }


}
