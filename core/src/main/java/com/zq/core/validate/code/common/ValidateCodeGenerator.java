package com.zq.core.validate.code.common;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:02
 * @Package com.zq.core.validate.code.common
 **/


public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     *
     * @param servletWebRequest
     * @return
     */
    ValidateCode generate(ServletWebRequest servletWebRequest);
}
