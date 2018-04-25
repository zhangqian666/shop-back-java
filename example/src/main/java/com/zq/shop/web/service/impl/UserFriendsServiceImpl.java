package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.bean.UserFriends;
import com.zq.shop.web.mappers.ShopUserMapper;
import com.zq.shop.web.mappers.UserFriendsMapper;
import com.zq.shop.web.service.IUserFriendsService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/24 下午2:50
 * @Package com.zq.shop.web.service.impl
 **/


@Service("iUserFriendsService")
public class UserFriendsServiceImpl implements IUserFriendsService {

    @Autowired
    private UserFriendsMapper userFriendsMapper;

    @Autowired
    private ShopUserMapper shopUserMapper;

    public ServerResponse list(Integer userId) {
        List<UserFriends> byUid = userFriendsMapper.findByUid(userId);
        List<ShopUser> shopUserList = Lists.newArrayList();
        for (UserFriends userFriends : byUid) {
            shopUserList.add(shopUserMapper.selectByPrimaryKey(userFriends.getUid()));
        }
        return ServerResponse.createBySuccess(shopUserList);
    }


    public ServerResponse create(Integer userId, Integer followId) {
        List<UserFriends> findlist = userFriendsMapper.findByuidAndFollowId(userId, followId);
        if (findlist.size() == 0) {
            UserFriends userFriends = new UserFriends();
            userFriends.setUid(userId);
            userFriends.setFollowId(followId);
            userFriendsMapper.insert(userFriends);
            return ServerResponse.createBySuccessMessage("添加好友成功");
        }
        return ServerResponse.createByErrorMessage("已经是好友");

    }

    public ServerResponse delete(Integer userId, Integer followId) {
        List<UserFriends> findlist = userFriendsMapper.findByuidAndFollowId(userId, followId);
        if (findlist.size() == 0) {
            userFriendsMapper.deleteByUidAndFollowId(userId, followId);
            return ServerResponse.createBySuccessMessage("删除好友成功");
        }
        return ServerResponse.createByErrorMessage("不是好友，无法删除");

    }
}
