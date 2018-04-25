package com.zq.shop.configs;

import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.mappers.ShopUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("UserDetails获取用户信息" + s);
        return buildUser(s);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        log.info("SocialUserDetails获取用户信息" + s);
        return buildUser(s);
    }

    private SocialUserDetails buildUser(String userId) {
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        List<ShopUser> byPhone = shopUserMapper.findByPhone(userId);
        if (byPhone.size() == 0) {
            throw new UsernameNotFoundException("用户不存在");
        }
        ShopUser shopUser = byPhone.get(0);
        return new SocialUser(shopUser.getPhone(), shopUser.getPassword(),
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }


}
