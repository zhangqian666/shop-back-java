package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.mappers.MomentsMapper;
import com.zq.shop.web.service.IMomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:08
 * @Package com.zq.shop.web.service.impl
 **/

@Service("iMomentsService")
public class MomentsServiceImpl implements IMomentsService {

    @Autowired
    private MomentsMapper momentsMapper;

    public ServerResponse create(Moments moments) {
        momentsMapper.insert(moments);
        return ServerResponse.createBySuccessMessage("发表成功");
    }


    public ServerResponse details(Integer momentId) {
        return ServerResponse.createBySuccess(momentsMapper.selectByPrimaryKey(momentId));
    }

    public ServerResponse list(Integer uid) {
        if (uid == null) {
            return ServerResponse.createBySuccess(momentsMapper.find());
        }
        return ServerResponse.createBySuccess(momentsMapper.findByUserId(uid));
    }

}
