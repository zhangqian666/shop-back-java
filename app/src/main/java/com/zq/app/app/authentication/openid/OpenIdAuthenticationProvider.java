package com.zq.app.app.authentication.openid;

import com.zq.app.app.social.AppSignUpUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午1:08
 * @Package com.zq.app.app.authentication.openid
 **/

@Slf4j
@Setter
@Getter
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;
        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add(((String) authenticationToken.getPrincipal()));

        //传入的是 第三方名称id 和 openid
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authenticationToken.getProviderId(), providerUserIds);


        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
            //如果没有注册，把信息存储在redis里面
            log.error(System.currentTimeMillis() + "");
            appSignUpUtils.saveConnectionData(authenticationToken.getConnectionData());
            throw new InternalAuthenticationServiceException("无法获取第三方注册信息");
        }
        //获取的是 本地应用的userid
        String userId = userIds.iterator().next();
        UserDetails user = userDetailsService.loadUserByUserId(userId);

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取应用用户信息");
        }

        OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
