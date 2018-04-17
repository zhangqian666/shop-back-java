package com.zq.core.properties.validate;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/4 下午4:32
 * @Package com.zq.core.properties.validate
 **/

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码配置
 */
@Setter
@Getter
public class ValidateCodeProperties {


    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
