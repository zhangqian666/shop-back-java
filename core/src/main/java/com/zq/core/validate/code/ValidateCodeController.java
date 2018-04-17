package com.zq.core.validate.code;

import com.zq.core.properties.SecurityConstants;
import com.zq.core.restful.ServerResponse;
import com.zq.core.validate.code.common.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午3:30
 * @Package com.zq.core.validate.code
 **/


@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;


    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link com.zq.core.validate.code.common.ValidateCodeProcessor}接口实现
     *
     * @param type
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public ServerResponse createCode(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return validateCodeProcessorHolder.findValidateCodeProcessor(type)
                .create(new ServletWebRequest(request, response));

    }
}
