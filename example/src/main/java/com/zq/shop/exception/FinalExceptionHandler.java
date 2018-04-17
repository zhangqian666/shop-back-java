package com.zq.shop.exception;

import com.zq.core.restful.ServerResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/10 下午10:01
 * @Package com.zq.shop.exception
 **/
@Deprecated
//@RestController
public class FinalExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ServerResponse finalError(HttpServletResponse response, HttpServletRequest request) {
        return ServerResponse.createByErrorCodeMessage(500, "服务器错误");
    }
}
