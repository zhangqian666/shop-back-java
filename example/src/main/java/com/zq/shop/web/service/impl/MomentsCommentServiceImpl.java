package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.MomentsComment;
import com.zq.shop.web.mappers.MomentsCommentMapper;
import com.zq.shop.web.service.IMomentsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:19
 * @Package com.zq.shop.web.service.impl
 **/

@Service("iMomentsCommentService")
public class MomentsCommentServiceImpl implements IMomentsCommentService {


    @Autowired
    private MomentsCommentMapper momentsCommentMapper;

    public ServerResponse create(MomentsComment momentsComment) {
        int id = momentsCommentMapper.insert(momentsComment);
        return ServerResponse.createBySuccess("评论成功", id);

    }

    public ServerResponse findComment(Integer momentsId){
        List<MomentsComment> byMomentsId = momentsCommentMapper.findByMomentsId(momentsId);
        return ServerResponse.createBySuccess(byMomentsId);

    }
}
