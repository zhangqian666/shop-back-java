package com.zq.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权信息管理器
 * <p>
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 *
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午2:00
 * @Package com.zq.core.authorize
 **/


public interface AuthorizeConfigManager {

    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
