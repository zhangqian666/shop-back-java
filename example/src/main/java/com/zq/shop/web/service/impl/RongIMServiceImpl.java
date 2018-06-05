package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.RongIMToken;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.mappers.RongIMTokenMapper;
import com.zq.shop.web.mappers.ShopUserMapper;
import com.zq.shop.web.service.IRongIMService;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/31 下午2:19
 * @Package com.zq.shop.web.service.impl
 **/


@Service("iRongIMService")
public class RongIMServiceImpl implements IRongIMService {

    @Autowired
    RongIMTokenMapper rongIMTokenMapper;

    @Autowired
    ShopUserMapper shopUserMapper;

    @Override
    public ServerResponse getToken(Integer userId) {

        RongIMToken oneByUserId = rongIMTokenMapper.findOneByUserId(userId);
        TokenResult tokenResult;
        if (oneByUserId == null) {
            ShopUser shopUser = shopUserMapper.selectByPrimaryKey(userId);
            tokenResult = requestToken(shopUser);
            if (tokenResult.getCode() != 200) {
                return ServerResponse.createByErrorMessage(tokenResult.msg);
            }
            RongIMToken record = new RongIMToken();
            record.setUserId(userId);
            record.setToken(tokenResult.getToken());
            rongIMTokenMapper.insert(record);
        } else {
            tokenResult = new TokenResult(200, oneByUserId.getToken(), oneByUserId.getUserId().toString(), "");
        }
        return ServerResponse.createBySuccess(tokenResult);

    }


    private TokenResult requestToken(ShopUser shopUser) {
        String appKey = "e0x9wycfe469q";
        String appSecret = "XSUeW427cE5";
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        User User = rongCloud.user;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        UserModel user = new UserModel()
                .setId(shopUser.getUid().toString())
                .setName(shopUser.getUsername())
                .setPortrait("http://39.106.46.79/images/product.png");
        TokenResult result = null;
        try {
            result = User.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
