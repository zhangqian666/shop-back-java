package com.zq.core.social.wexin.connect;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午12:59
 * @Package com.zq.core.social.wexin.connect
 **/

@Setter
@Getter
public class WeXinAccessGrant extends AccessGrant {
    private String openId;

    public WeXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }
}
