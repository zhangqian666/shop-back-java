package com.zq.shop.exception;

import com.zq.core.restful.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午9:40
 * @Package com.zq.shop.exception
 **/
@ResponseBody
@ControllerAdvice
public class ExampleExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ServerResponse errorHandler(Exception e) {
        e.printStackTrace();
        int code;
        if (e instanceof NoHandlerFoundException) {
            code = HttpStatus.BAD_REQUEST.value();
        } else {
            code = 500;
        }
        return ServerResponse.createByErrorCodeMessage(code, e.getClass().getSimpleName() + ":" + e.getMessage());
    }
}
