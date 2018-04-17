package com.zq.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:47
 * @Package com.zq.core.validate.code.sms
 **/

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {

        log.info("用第三方短信给" + mobile + "发送短信验证码：" + code);
    }
}
