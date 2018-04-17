package com.zq.core.properties.validate;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:06
 * @Package com.zq.core.properties.validate
 **/


@Setter
@Getter
public class SmsCodeProperties {

    private int length = 4;
    private int expireIn = 60;

    private String url;
}
