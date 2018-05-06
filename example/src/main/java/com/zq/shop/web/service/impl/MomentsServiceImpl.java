package com.zq.shop.web.service.impl;

import com.google.common.collect.Lists;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.MomentsCommentMapper;
import com.zq.shop.web.mappers.MomentsMapper;
import com.zq.shop.web.service.IMomentsService;
import com.zq.shop.web.vo.MomentsListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:08
 * @Package com.zq.shop.web.service.impl
 **/

@Service("iMomentsService")
public class MomentsServiceImpl implements IMomentsService {

    @Autowired
    private MomentsMapper momentsMapper;
    @Autowired
    private MomentsCommentMapper momentsCommentMapper;
    @Autowired
    private IDMapper idMapper;

    public ServerResponse create(Moments moments, Integer uid) {
        moments.setId(idMapper.findId(Const.IDType.MOMENTS_ID));
        momentsMapper.insert(moments);
        return ServerResponse.createBySuccessMessage("发表成功");
    }


    public ServerResponse details(Integer momentId, Integer uid) {
        return ServerResponse.createBySuccess(momentsMapper.selectByPrimaryKey(momentId));
    }

    public ServerResponse list(Integer uid) {
        List<Moments> moments;
        if (uid == null) {
            moments = momentsMapper.find();
        } else {
            moments = momentsMapper.findByUserId(uid);
        }
        List<MomentsListVo> momentsListVos = Lists.newArrayList();
        for (Moments moment : moments) {
            MomentsListVo momentsListVo = new MomentsListVo();
            momentsListVo.setMoments(moment);
            momentsListVo.setMomentsComments(momentsCommentMapper.findByMomentsId(moment.getId()));
            momentsListVos.add(momentsListVo);
        }

        return ServerResponse.createBySuccess(momentsListVos);
    }

}
