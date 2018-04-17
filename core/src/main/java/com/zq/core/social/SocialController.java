package com.zq.core.social;

import com.zq.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 上午10:04
 * @Package com.zq.core.social
 **/


public abstract class SocialController  {

    /**
     * 根据connect内容构建 SocialUserInfo
     * @param connection
     * @return
     */
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection){

        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;

    }
}
