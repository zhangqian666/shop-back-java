package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午2:34
 * @Package com.zq.shop.web.service
 **/


public interface IShopUserService {
    ServerResponse register(ShopUser shopUser);

    ServerResponse<ShopUser> getUserInfo(String username);
    ServerResponse<ShopUser> getUserInfo(Integer userId);

    ServerResponse updateUserImage(String uploadFile, Integer userId);

    ServerResponse updateUserPassword(String password, Integer userId);

    ServerResponse updateInfo(ShopUser username, Integer uid);
}