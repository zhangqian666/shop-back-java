package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Cart;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午5:42
 * @Package com.zq.shop.web.service
 **/


public interface ICartService {
    ServerResponse<List<Cart>> add(Integer userId, Integer productId, Integer count);

    ServerResponse<List<Cart>> delete(Integer userId, String productIds);

    ServerResponse<List<Cart>> updateCount(Integer userId, Integer productId, Integer count);

    ServerResponse<List<Cart>> list(Integer userId);

    ServerResponse<List<Cart>> selectOrUnSelect(Integer userId, Integer productId, Integer checked);
}
