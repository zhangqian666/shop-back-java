package com.zq.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import javax.naming.AuthenticationException;
import java.io.IOException;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午6:14
 * @Package com.zq.core.social.qq.api
 **/

@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {


    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    @Autowired
    private ObjectMapper objectMapper;

    public QQImpl(String accessToken, String appid) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appid;

        String result = getRestTemplate().getForObject(String.format(URL_GET_OPENID, accessToken), String.class);

        if (result.contains("error"))
            throw new RuntimeException("获取openid失败");
        log.info(result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");

    }

    @Override
    public QQUserInfo getUserInfo() {
        String result = getRestTemplate().getForObject(String.format(URL_GET_USERINFO, appId, openId), String.class);
        log.info(result);
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
