package com.zq.core.social.wexin.config;

import com.zq.core.properties.SecurityProperties;
import com.zq.core.properties.social.WeXinProperties;
import com.zq.core.social.view.ImoocConnectView;
import com.zq.core.social.wexin.connect.WeXinConnectionFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午12:48
 * @Package com.zq.core.social.wexin.config
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "zq.security.social-properties.wexin", name = "appId")
public class WeXinAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        log.info("创建WeXinAutoConfig成功");
        WeXinProperties weixinConfig = securityProperties.getSocialProperties().getWexin();
        return new WeXinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
    }

    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new ImoocConnectView();
    }
}
