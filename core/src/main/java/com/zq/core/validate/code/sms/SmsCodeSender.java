package com.zq.core.validate.code.sms;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:45
 * @Package com.zq.core.validate.code.sms
 **/


public interface SmsCodeSender {
    void send(String mobile, String code);
}
