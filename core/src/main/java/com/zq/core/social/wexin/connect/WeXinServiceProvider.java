package com.zq.core.social.wexin.connect;

import com.zq.core.social.wexin.api.WeXin;
import com.zq.core.social.wexin.api.WeXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午1:02
 * @Package com.zq.core.social.wexin.connect
 **/


public class WeXinServiceProvider extends AbstractOAuth2ServiceProvider<WeXin> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    public WeXinServiceProvider(String appId, String appSecret) {
        super(new WeXinOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public WeXin getApi(String accessToken) {
        return new WeXinImpl(accessToken);
    }
}
