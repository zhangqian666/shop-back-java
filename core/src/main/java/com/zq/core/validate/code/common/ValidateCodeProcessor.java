package com.zq.core.validate.code.common;

import com.zq.core.restful.ServerResponse;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 上午11:52
 * @Package com.zq.core.validate.code
 **/


public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    ServerResponse create(ServletWebRequest servletWebRequest) throws Exception;

    /**
     * 验证校验码
     *
     * @param servletWebRequest
     */

    void validate(ServletWebRequest servletWebRequest);


}
