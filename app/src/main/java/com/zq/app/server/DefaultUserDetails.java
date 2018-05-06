package com.zq.app.server;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/2 下午4:41
 * @Package com.zq.shop.web.common
 **/

@Getter
@Setter
public class DefaultUserDetails implements SocialUserDetails {

    private Integer uid;
    private String username;
    private String password;

    public DefaultUserDetails(Integer uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUserId() {
        return getUid().toString();
    }
}
