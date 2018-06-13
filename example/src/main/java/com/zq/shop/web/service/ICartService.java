package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午5:42
 * @Package com.zq.shop.web.service
 **/


public interface ICartService {
    ServerResponse add(Integer userId, Integer productId, Integer count);

    ServerResponse delete(Integer userId, String productIds);

    ServerResponse updateCount(Integer userId, Integer productId, Integer count);

    ServerResponse list(Integer userId);

    ServerResponse selectOrUnSelect(Integer userId, Integer productId, Integer checked);
}
