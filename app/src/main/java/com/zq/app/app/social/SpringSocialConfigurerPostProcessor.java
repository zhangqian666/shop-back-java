package com.zq.app.app.social;

import com.zq.core.properties.SecurityConstants;
import com.zq.core.social.support.ZqSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 用code进行社交登录 获取accesstoken后 去本地验证 如果验证不通过跳转的地址
 *
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午1:27
 * @Package com.zq.app.app.social
 **/

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (StringUtils.equals(beanName, "zqSocialSecurityConfig")) {
            ZqSpringSocialConfigurer config = (ZqSpringSocialConfigurer) bean;
            config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return config;
        }
        return bean;
    }
}
