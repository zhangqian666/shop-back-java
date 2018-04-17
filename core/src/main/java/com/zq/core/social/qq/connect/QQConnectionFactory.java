package com.zq.core.social.qq.connect;

import com.zq.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午7:52
 * @Package com.zq.core.social.qq.connect
 **/


public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId, String appid, String appSecret) {
        super(providerId, new QQServiceProvider(appid, appSecret), new QQAdapter());
    }
}
