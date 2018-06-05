package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/31 下午2:19
 * @Package com.zq.shop.web.service
 **/


public interface IRongIMService {

    ServerResponse getToken(Integer userId);
}
