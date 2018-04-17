package com.zq.app.app.exceptions;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午9:25
 * @Package com.zq.app.app.exceptions
 **/


public class ProductTokenException extends AuthenticationException {
    public ProductTokenException(String message) {
        super(message);
    }
}
