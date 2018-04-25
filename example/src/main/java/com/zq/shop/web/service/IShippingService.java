package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Shipping;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午8:01
 * @Package com.zq.shop.web.service
 **/


public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<List<Shipping>> list(Integer userId, int pageNum, int pageSize);
}
