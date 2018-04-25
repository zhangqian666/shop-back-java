package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:08
 * @Package com.zq.shop.web.service
 **/


public interface IMomentsService {
    ServerResponse create(Moments moments);

    ServerResponse details(Integer momentId);

    ServerResponse list(Integer uid);
}
