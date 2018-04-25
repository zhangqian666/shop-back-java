package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.ShopUserMapper;
import com.zq.shop.web.service.IShopUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private PasswordEncoder passwordEncoder;

    public ServerResponse register(ShopUser shopUser) {
        ServerResponse validResponse = this.available(shopUser.getUsername(), Const.User.EMAIL);
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
        int resultCount = shopUserMapper.insert(shopUser);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        List<ShopUser> byPhone = shopUserMapper.findByPhone(shopUser.getPhone());

        return ServerResponse.createBySuccess("注册成功", byPhone.get(0));
    }

    private ServerResponse available(String username, String type) {
        if (StringUtils.isNoneBlank(type)) {
            //开始校验
            if (Const.User.EMAIL.equals(type)) {
                int resultCount = shopUserMapper.findByEmail(username).size();
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
            if (Const.User.PHONE.equals(type)) {
                int resultCount = shopUserMapper.findByPhone(username).size();
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("手机号已存在");
                }
            }
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<ShopUser> getUserInfo(String name) {
        if (StringUtils.isBlank(name)) {
            return ServerResponse.createByErrorMessage("authtication为空");
        }
        ShopUser shopUser = shopUserMapper.findByPhone(name).get(0);
        if (shopUser == null) {
            return ServerResponse.createByErrorMessage("未获取用户信息");
        }
        shopUser.setPassword(null);
        return ServerResponse.createBySuccess(shopUser);

    }

    @Override
    public ServerResponse updateUserImage(String uploadFile, String name) {
        if (shopUserMapper.updateImageByPhone(uploadFile, name) == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess("更新头像成功", uploadFile);
    }

    @Override
    public ServerResponse updateUserPassword(String password, String name) {
        if (shopUserMapper.updatePasswordByPhone(password, name) == 0) {
            return ServerResponse.createByErrorMessage("修改密码失败");
        }
        return ServerResponse.createBySuccessMessage("修改密码成功");
    }
}
