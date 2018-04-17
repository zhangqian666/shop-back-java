package com.zq.core.properties.social;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/4 下午4:31
 * @Package com.zq.core.properties.social
 **/

@Getter
@Setter
public class SocialProperties {

    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WeXinProperties wexin = new WeXinProperties();
}
