package com.zq.core.validate.code.common;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:25
 * @Package com.zq.core.validate.code.common
 **/

import com.zq.core.properties.SecurityConstants;

/**
 * 校验码类型
 */
public enum ValidateCodeType {



    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
