package com.zq.app.app.authentication.openid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.connect.ConnectionData;

import java.util.Collection;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午1:09
 * @Package com.zq.app.app.authentication.openid
 **/

@Setter
@Getter
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private String providerId;
    private ConnectionData connectionData;

    public OpenIdAuthenticationToken(String openId, String providerId, ConnectionData connectionData) {
        super(null);
        this.principal = openId;
        this.providerId = providerId;
        this.connectionData = connectionData;
        setAuthenticated(false);
    }

    public OpenIdAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
