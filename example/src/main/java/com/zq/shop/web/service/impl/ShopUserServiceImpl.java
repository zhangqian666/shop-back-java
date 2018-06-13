package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.ShopUserMapper;
import com.zq.shop.web.service.IShopUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午2:34
 * @Package com.zq.shop.web.service.impl
 **/

@Service("iShopUserService")
public class ShopUserServiceImpl implements IShopUserService {

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private IDMapper idMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServerResponse register(ShopUser shopUser) {
        ServerResponse validResponse = this.available(shopUser.getEmail(), Const.User.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.available(shopUser.getPhone(), Const.User.PHONE);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        shopUser.setRole(Const.Role.CUSTOME_USER);
        //MD5加密
        shopUser.setPassword(passwordEncoder.encode(shopUser.getPassword()));
        //生成 用户ID
        shopUser.setUid(idMapper.findId(Const.IDType.USER_ID));

        shopUser.setUsername(Const.User.DEFAULT_NAME + shopUser.getUid());

        int resultCount = shopUserMapper.insert(shopUser);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccess("注册成功", shopUser);
    }

    private ServerResponse available(String username, String type) {
        if (StringUtils.isNoneBlank(type)) {
            //开始校验
            if (Const.User.EMAIL.equals(type)) {
                if (StringUtils.isBlank(username)) return ServerResponse.createBySuccess();
                ShopUser resultData = shopUserMapper.findOneByEmail(username);
                if (resultData != null) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
            if (Const.User.PHONE.equals(type)) {
                ShopUser resultData = shopUserMapper.findOneByPhone(username);
                if (resultData != null) {
                    return ServerResponse.createByErrorMessage("手机号已存在");
                }
            }
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<ShopUser> getUserInfo(String name) {
        if (StringUtils.isBlank(name)) {
            return ServerResponse.createByErrorMessage("authtication为空");
        }
        ShopUser shopUser = shopUserMapper.findOneByPhone(name);
        if (shopUser == null) {
            return ServerResponse.createByErrorMessage("未获取用户信息");
        }
        shopUser.setPassword(null);
        return ServerResponse.createBySuccess(shopUser);

    }

    @Override
    public ServerResponse<ShopUser> getUserInfo(Integer userId) {
        ShopUser shopUser = shopUserMapper.selectByPrimaryKey(userId);
        if (shopUser == null) {
            return ServerResponse.createByErrorMessage("未获取用户信息");
        }
        shopUser.setPassword(null);
        return ServerResponse.createBySuccess(shopUser);
    }

    @Override
    public ServerResponse updateUserImage(String uploadFile, Integer userId) {
        if (shopUserMapper.updateImageByUid(Const.User.IMAGE_PER + uploadFile, userId) == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess("更新头像成功", uploadFile);
    }

    @Override
    public ServerResponse updateUserPassword(String password, Integer userId) {
        if (shopUserMapper.updatePasswordByUid(password, userId) == 0) {
            return ServerResponse.createByErrorMessage("修改密码失败");
        }
        return ServerResponse.createBySuccessMessage("修改密码成功");
    }
}
