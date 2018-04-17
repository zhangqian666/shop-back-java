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
public class WeXinProperties extends SocialProperties{
    private String providerId = "wexin";

}
