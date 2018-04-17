package com.zq.core.social.wexin.connect;

import com.zq.core.social.wexin.api.WeXin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/9 下午1:08
 * @Package com.zq.core.social.wexin.connect
 **/

/**
 * 微信链接工厂
 */
public class WeXinConnectionFactory extends OAuth2ConnectionFactory<WeXin> {


    public WeXinConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeXinServiceProvider(appId, appSecret), new WeXinAdapter(appId));
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeXinAccessGrant) {
            return ((WeXinAccessGrant) accessGrant).getOpenId();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
     */
    public Connection<WeXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeXin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
     */
    public Connection<WeXin> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeXin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeXin> getApiAdapter(String providerUserId) {
        return new WeXinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeXin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeXin>) getServiceProvider();
    }
}
