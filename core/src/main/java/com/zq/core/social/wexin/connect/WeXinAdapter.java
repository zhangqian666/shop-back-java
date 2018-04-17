package com.zq.core.social.wexin.connect;

import com.zq.core.social.wexin.api.WeXin;
import com.zq.core.social.wexin.api.WeXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 *
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午1:05
 * @Package com.zq.core.social.wexin.connect
 **/


public class WeXinAdapter implements ApiAdapter<WeXin> {
    private String openId;

    public WeXinAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeXin weXin) {
        weXin.getWexinUserInfo(openId);
        return true;
    }

    @Override
    public void setConnectionValues(WeXin weXin, ConnectionValues connectionValues) {
        WeXinUserInfo wexinUserInfo = weXin.getWexinUserInfo(openId);
        connectionValues.setProviderUserId(wexinUserInfo.getOpenid());
        connectionValues.setDisplayName(wexinUserInfo.getNickname());
        connectionValues.setImageUrl(wexinUserInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeXin weXin) {
        return null;
    }

    @Override
    public void updateStatus(WeXin weXin, String s) {

    }
}
