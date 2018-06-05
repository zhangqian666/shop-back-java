package com.zq.shop.web.controller.NormalUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.bean.MomentsComment;
import com.zq.shop.web.service.IMomentsCommentService;
import com.zq.shop.web.service.IMomentsService;
import com.zq.shop.web.vo.MomentVo;
import com.zq.shop.web.vo.MomentsListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:32
 * @Package com.zq.shop.web.controller.NormalUser
 **/

@Api(tags = "朋友圈")
@RestController
@RequestMapping("/user/moments")
public class MomentsController {

    @Autowired
    private IMomentsService iMomentsService;

    @Autowired
    private IMomentsCommentService iMomentsCommentService;

    @ApiOperation("获取文章列表")
    @PostMapping("/list")
    public ServerResponse<List<MomentVo>> momentsList(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails,
                                                      Integer uid) {
        return iMomentsService.list(defaultUserDetails.getUid(),uid);

    }


    @ApiOperation("创建文章")
    @PostMapping("/create")
    public ServerResponse createMoments(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails,
                                        String title,
                                        String subTitle,
                                        String details,
                                        String mainImage,
                                        String subImages) {
        Moments moments = new Moments();
        moments.setUserId(defaultUserDetails.getUid());
        moments.setTitle(title);
        moments.setSubtitle(subTitle);
        moments.setDetails(details);
        moments.setMainImage(mainImage);
        moments.setSubImages(subImages);
        return iMomentsService.create(moments, defaultUserDetails.getUid());
    }

    @ApiOperation("获取文章详情")
    @PostMapping("/details")
    public ServerResponse<MomentsListVo> momentsDetails(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer momentId) {
        return iMomentsService.details(momentId, defaultUserDetails.getUid());
    }

    @ApiOperation("文章点赞")
    @PostMapping("/star")
    public ServerResponse starMoments(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer momentId) {
        return iMomentsService.star(momentId, defaultUserDetails.getUid());
    }

    @ApiOperation("创建评论")
    @PostMapping("/comment/create")
    public ServerResponse createMomentsComment(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails,
                                               Integer momentsId,
                                               String content,
                                               String images) {

        MomentsComment momentsComment = new MomentsComment();
        momentsComment.setUserId(defaultUserDetails.getUid());
        momentsComment.setMomentsId(momentsId);
        momentsComment.setContent(content);
        momentsComment.setImages(images);
        return iMomentsCommentService.create(momentsComment);
    }
}
