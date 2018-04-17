package com.zq.core.social.qq.config;

import com.zq.core.properties.SecurityProperties;
import com.zq.core.properties.social.QQProperties;
import com.zq.core.social.qq.connect.QQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午7:40
 * @Package com.zq.core.social.qq.config
 **/

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "zq.security.social-properties.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        log.info("创建QQConnectFactory成功");
        QQProperties qq = securityProperties.getSocialProperties().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());

    }
}
