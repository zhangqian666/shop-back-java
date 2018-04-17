package com.zq.app.app.authentication.openid;

import com.zq.core.properties.SecurityConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionData;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午1:19
 * @Package com.zq.app.app.authentication.openid
 **/

@Setter
@Getter
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;
    private String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;
    private boolean postOnly = true;

    protected OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String openid = obtainOpenId(httpServletRequest);
        String providerId = obtainProviderId(httpServletRequest);


        //获取connentData数据 用于在验证不通过的时候存储
        ConnectionData connectionData = obtainConnectData(httpServletRequest);

        if (openid == null) {
            openid = "";
        }
        if (providerId == null) {
            providerId = "";
        }

        openid = openid.trim();
        providerId = providerId.trim();

        OpenIdAuthenticationToken openIdAuthenticationToken = new OpenIdAuthenticationToken(openid, providerId, connectionData);

        // Allow subclasses to set the "details" property
        setDetails(httpServletRequest, openIdAuthenticationToken);

        return this.getAuthenticationManager().authenticate(openIdAuthenticationToken);
    }

    /**
     * this.providerId = providerId;
     * this.providerUserId = providerUserId;
     * this.displayName = displayName;
     * this.profileUrl = profileUrl;
     * this.imageUrl = imageUrl;
     * this.accessToken = accessToken;
     * this.secret = secret;
     * this.refreshToken = refreshToken;
     * this.expireTime = expireTime;
     *
     * @param httpServletRequest
     * @return
     */

    private ConnectionData obtainConnectData(HttpServletRequest httpServletRequest) {
        return new ConnectionData(
                obtainProviderId(httpServletRequest),
                obtainOpenId(httpServletRequest),
                httpServletRequest.getParameter("displayName"),
                httpServletRequest.getParameter("profileUrl"),
                httpServletRequest.getParameter("imageUrl"),
                httpServletRequest.getParameter("accessToken"),
                httpServletRequest.getParameter("secret"),
                httpServletRequest.getParameter("refreshToken"),
                Long.parseLong(httpServletRequest.getParameter("expireTime"))
        );
    }

    /**
     * 获取openId
     */
    protected String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParameter);
    }

    /**
     * 获取提供商id
     */
    protected String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

    protected void setDetails(HttpServletRequest request, OpenIdAuthenticationToken openIdAuthenticationToken) {
        openIdAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setOpenIdParameter(String openIdParameter) {
        Assert.hasText(openIdParameter, "Username parameter must not be empty or null");
        this.openIdParameter = openIdParameter;
    }
}
