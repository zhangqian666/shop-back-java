package com.zq.core.validate.code.common;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:30
 * @Package com.zq.core.validate.code.common
 **/

/**
 * 校验码存取器
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);

}
