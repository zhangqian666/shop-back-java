package com.zq.core.validate.code.sms;

import com.zq.core.properties.SecurityProperties;
import com.zq.core.validate.code.common.ValidateCode;
import com.zq.core.validate.code.common.ValidateCodeGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:39
 * @Package com.zq.core.validate.code.sms
 **/

@Getter
@Setter
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {

        String code = RandomStringUtils.randomNumeric(securityProperties.getValidateCodeProperties().getSms().getLength());
        return new ValidateCode(code, securityProperties.getValidateCodeProperties().getSms().getExpireIn());
    }
}
