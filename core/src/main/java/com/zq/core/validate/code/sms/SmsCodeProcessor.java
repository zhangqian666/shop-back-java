package com.zq.core.validate.code.sms;

import com.zq.core.properties.SecurityConstants;
import com.zq.core.restful.ServerResponse;
import com.zq.core.validate.code.common.AbstractValidateCodeProcessor;
import com.zq.core.validate.code.common.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:42
 * @Package com.zq.core.validate.code.sms
 **/

@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected ServerResponse send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);

        smsCodeSender.send(mobile, validateCode.getCode());

        return ServerResponse.createBySuccess("发送短信成功 mobile:" + mobile + "  code:" + validateCode.getCode());
    }
}
