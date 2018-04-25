package com.zq.app.server;

import com.zq.core.properties.SecurityProperties;
import com.zq.core.properties.oauth2.OAuth2ClientProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器设置
 *
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午4:42
 * @Package com.zq.app.server
 **/

@Configuration
@EnableAuthorizationServer
public class ZqAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;


    /**
     * 认证及token配置
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }

    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param security
     * @throws Exception
     */

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        builder.withClient("defaultClient")
                .secret("defaultSecret")
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(-1)
                .refreshTokenValiditySeconds(2592000)
                .scopes("all");

        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2Properties().getClients())) {
            for (OAuth2ClientProperties client : securityProperties.getOauth2Properties().getClients()) {
                builder.withClient(client.getClientId())
                        .secret(client.getClientSecret())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
                        .refreshTokenValiditySeconds(2592000)
                        .scopes("all");
            }
        }
    }


}
