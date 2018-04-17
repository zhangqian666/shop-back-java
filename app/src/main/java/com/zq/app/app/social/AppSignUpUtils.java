package com.zq.app.app.social;

import com.zq.app.app.exceptions.AppSecretException;
import com.zq.core.properties.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午12:52
 * @Package com.zq.app.app.social
 **/

@Slf4j
@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;


    /**
     * 缓存社交信息到redis
     *
     * @param data
     */
    public void saveConnectionData(ConnectionData data) {
        String key = getKey(data.getProviderId(), data.getProviderUserId());
        log.error(key + "==" + data.getAccessToken());
        redisTemplate.opsForValue().set(key, data, 10, TimeUnit.MINUTES);
    }


    /**
     * 将缓存的社交网站用户信息与系统注册用户信息绑定
     * 通过access方式注册
     *
     * @param userId
     */
    public void doPostSignUp(String userId, String providerId, String openId) {
        String key = getKey(providerId, openId);
        if (!redisTemplate.hasKey(key)) {
            throw new AppSecretException("无法找到缓存的用户社交账号信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);

        //创建的时候会初始化 qq或者微信的impl
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        //apiAdapter测试
        connection.test();
        connection.refresh();
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
        redisTemplate.delete(key);
    }

    /**
     * 获取redis存储的key
     *
     * @param providerId
     * @param providerUserId
     * @return
     */
    private String getKey(String providerId, String providerUserId) {
        return "zq:security:social.connect." + providerId + providerUserId;
    }

}
