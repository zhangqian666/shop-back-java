package com.zq.core.properties.social;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午8:01
 * @Package com.zq.core.properties.social
 **/

@Setter
@Getter
public class QQProperties extends SocialProperties{
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";


}
