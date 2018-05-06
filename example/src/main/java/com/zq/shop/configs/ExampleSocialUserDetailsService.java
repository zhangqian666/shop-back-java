package com.zq.shop.configs;

import com.zq.app.server.DefaultUserDetails;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.mappers.ShopUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午9:16
 * @Package com.zq.shop.configs
 **/
@Slf4j
@Component
public class ExampleSocialUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private ShopUserMapper shopUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetails获取用户信息" + username);
        return buildUser(username, true);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        log.info("SocialUserDetails获取用户信息" + s);
        return buildUser(s, false);
    }

    /**
     * @param userId
     * @param isUserDetails true 用 username去查询账号信息； false 用uid去查询用户
     * @return
     */
    private SocialUserDetails buildUser(String userId, boolean isUserDetails) {
        ShopUser shopUser;
        if (isUserDetails) {
            shopUser = shopUserMapper.findOneByPhone(userId);

        } else {
            shopUser = shopUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
        }
        if (shopUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new DefaultUserDetails(shopUser.getUid(), shopUser.getPhone(), shopUser.getPassword());
    }


}
