package com.zq.shop.web.controller.NormalUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.bean.MomentsComment;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.service.IMomentsCommentService;
import com.zq.shop.web.service.IMomentsService;
import com.zq.shop.web.service.IShopUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private IShopUserService iShopUserService;


    @ApiOperation("获取文章列表")
    @PostMapping("/list")
    public ServerResponse momentsList(Authentication authentication, Integer uid) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iMomentsService.list(uid);
    }

    @ApiOperation("创建文章")
    @PostMapping("/create")
    public ServerResponse createMoments(Authentication authentication, Moments moments) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iMomentsService.create(moments);
    }

    @ApiOperation("获取文章详情")
    @PostMapping("/details")
    public ServerResponse momentsDetails(Authentication authentication, Integer momentId) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iMomentsService.details(momentId);
    }

    @ApiOperation("评论")
    @PostMapping("/comment/create")
    public ServerResponse createMomentsComment(Authentication authentication, MomentsComment momentsComment) {
        ServerResponse<ShopUser> userInfo = iShopUserService.getUserInfo(authentication.getName());
        return iMomentsCommentService.create(momentsComment);
    }
}
