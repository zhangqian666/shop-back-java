package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.bean.MomentsComment;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.MomentsCommentMapper;
import com.zq.shop.web.mappers.MomentsMapper;
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
    @Autowired
    private MomentsMapper momentsMapper;

    @Autowired
    private IDMapper idMapper;

    public ServerResponse create(MomentsComment momentsComment, Integer uid) {
        Integer momentsCommentId = idMapper.findId(Const.IDType.MOMENTS_COMMENT_ID);
        momentsComment.setId(momentsCommentId);
        momentsComment.setFollowId(uid);

        Moments moments = momentsMapper.selectByPrimaryKey(momentsComment.getMomentsId());
        if (moments == null) {
            return ServerResponse.createByErrorMessage("未找到该文章");
        }
        Integer userId = moments.getUserId();
        momentsComment.setUserId(userId);


        momentsCommentMapper.insert(momentsComment);
        return ServerResponse.createBySuccess("评论成功", momentsCommentId);

    }

    public ServerResponse findComment(Integer momentsId) {
        List<MomentsComment> byMomentsId = momentsCommentMapper.findByMomentsId(momentsId);
        return ServerResponse.createBySuccess(byMomentsId);

    }
}
