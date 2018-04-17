package com.zq.core.properties;

import com.zq.core.properties.browsers.BrowserProperties;
import com.zq.core.properties.oauth2.Oauth2Properties;
import com.zq.core.properties.social.SocialProperties;
import com.zq.core.properties.validate.ValidateCodeProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/4 下午4:33
 * @Package com.zq.core.properties
 **/

@Getter
@Setter
@ConfigurationProperties(prefix = "zq.security")
public class SecurityProperties {
    /**
     * 浏览器环境配置
     */
    private BrowserProperties browserProperties = new BrowserProperties();

    private Oauth2Properties oauth2Properties = new Oauth2Properties();

    private SocialProperties socialProperties = new SocialProperties();

    private ValidateCodeProperties validateCodeProperties = new ValidateCodeProperties();
}
