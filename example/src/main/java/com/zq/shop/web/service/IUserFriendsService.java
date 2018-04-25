package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/24 下午2:50
 * @Package com.zq.shop.web.service
 **/


public interface IUserFriendsService {

    ServerResponse list(Integer userId);

    ServerResponse create(Integer userId, Integer followId);

    ServerResponse delete(Integer userId, Integer followId);
}
