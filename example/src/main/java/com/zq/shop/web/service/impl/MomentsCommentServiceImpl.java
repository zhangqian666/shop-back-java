package com.zq.shop.web.service.impl;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.MomentsComment;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.MomentsCommentMapper;
import com.zq.shop.web.mappers.MomentsMapper;
import com.zq.shop.web.mappers.ShopUserMapper;
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

    @Autowired
    private ShopUserMapper shopUserMapper;

    public ServerResponse create(MomentsComment momentsComment) {
        Integer momentsCommentId = idMapper.findId(Const.IDType.MOMENTS_COMMENT_ID);
        momentsComment.setId(momentsCommentId);
        Integer userIdById = momentsMapper.findUserIdById(momentsComment.getMomentsId());
        if (userIdById == null) {
            return ServerResponse.createByErrorMessage("找不到该文章作者");
        }

        if (momentsComment.getReplyUserId() != null) {
            ShopUser shopUser = shopUserMapper.selectByPrimaryKey(momentsComment.getReplyUserId());
            if (shopUser != null) momentsComment.setReplyUserName(shopUser.getUsername());
        }
        momentsComment.setFollowId(userIdById);
        momentsCommentMapper.insert(momentsComment);
        return ServerResponse.createBySuccess("评论成功", momentsComment);

    }

    public ServerResponse findComment(Integer momentsId) {
        List<MomentsComment> byMomentsId = momentsCommentMapper.findByMomentsId(momentsId);
        return ServerResponse.createBySuccess(byMomentsId);

    }
}
