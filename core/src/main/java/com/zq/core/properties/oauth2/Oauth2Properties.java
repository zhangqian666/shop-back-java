package com.zq.core.properties.oauth2;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/4 下午4:30
 * @Package com.zq.core.properties.oauth2
 **/

@Setter
@Getter
public class Oauth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "imooc";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
