package com.zq.shop.web.service.impl;

import com.google.common.collect.Lists;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.bean.MomentsComment;
import com.zq.shop.web.bean.ShopUser;
import com.zq.shop.web.bean.Star;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.*;
import com.zq.shop.web.service.IMomentsService;
import com.zq.shop.web.vo.MomentCommentVo;
import com.zq.shop.web.vo.MomentVo;
import com.zq.shop.web.vo.MomentsListVo;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private StarMapper starMapper;

    public ServerResponse<MomentVo> create(Moments moments, Integer uid) {
        moments.setId(idMapper.findId(Const.IDType.MOMENTS_ID));
        momentsMapper.insert(moments);
        Moments mo = momentsMapper.selectByPrimaryKey(moments.getId());
        MomentVo target = new MomentVo();
        BeanUtils.copyProperties(mo, target);
        return ServerResponse.createBySuccess(target);
    }


    public ServerResponse<MomentsListVo> details(Integer momentId, Integer uid) {
        MomentsListVo momentsListVo = new MomentsListVo();
        {
            Moments moments = momentsMapper.selectByPrimaryKey(momentId);
            if (moments == null) {
                return ServerResponse.createByErrorMessage("未找到该文章");
            }
            MomentVo target = new MomentVo();
            BeanUtils.copyProperties(moments, target);
            momentsListVo.setMomentVo(target);
            //更新浏览次数
            momentsMapper.updateSeeTimesById(moments.getSeeTimes() + 1, moments.getId());
        }

        {
            List<MomentCommentVo> momentCommentVos = Lists.newArrayList();
            List<MomentsComment> byMomentsId = momentsCommentMapper.findByMomentsId(momentId);
            if (byMomentsId != null) {
                for (MomentsComment mc : byMomentsId) {
                    MomentCommentVo momentCommentVo = new MomentCommentVo();
                    BeanUtils.copyProperties(mc, momentCommentVo);
                    ShopUser shopUser = shopUserMapper.selectByPrimaryKey(momentCommentVo.getUserId());
                    momentCommentVo.setUsername(shopUser.getUsername());
                    momentCommentVo.setUserImage(shopUser.getImage());
                    momentCommentVos.add(momentCommentVo);
                }
            }
            momentsListVo.setMomentCommentVos(momentCommentVos);
        }
        return ServerResponse.createBySuccess(momentsListVo);
    }

    public ServerResponse<List<MomentVo>> list(Integer uid, Integer searchUid) {
        //排序实现: 数据库字段 + " desc" 或 数据库字段 + " asc"
        PageHelper.startPage(0, 10, "id desc");
        List<Moments> moments;
        if (searchUid == null) {
            moments = momentsMapper.find();
        } else {
            moments = momentsMapper.findByUserId(searchUid);
        }
        List<MomentVo> momentCommentVos = Lists.newArrayList();
        for (Moments moment : moments) {
            MomentVo target = new MomentVo();
            BeanUtils.copyProperties(moment, target);
            {
                //查找有多少评论
                List<MomentsComment> byMomentsId = momentsCommentMapper.findByMomentsId(moment.getId());
                target.setMomentCommentTimes(byMomentsId.size());
            }
            {
                //查找该文章对应用户信息
                ShopUser user = shopUserMapper.selectByPrimaryKey(moment.getUserId());
                target.setUsername(user.getUsername());
                target.setUserImage(user.getImage());
            }
            {
                List<Star> starList = starMapper.findBystarIdandType(moment.getId(), 1);
                target.setStar(starList.size());
                target.setStarEnable(1);
                for (Star aStarList : starList) {
                    if (aStarList.getUserId().intValue() == uid.intValue()) {
                        target.setStarEnable(0);
                    }
                }

            }
            momentCommentVos.add(target);
        }


        return ServerResponse.createBySuccess(momentCommentVos);
    }

    @Override
    public ServerResponse star(Integer momentId, Integer uid) {
        if (momentId == null) {
            return ServerResponse.createByErrorMessage("momentId 不存在");
        }
        Moments moments = momentsMapper.selectByPrimaryKey(momentId);
        if (moments == null) {
            return ServerResponse.createByErrorMessage("文章不存在");
        }


        List<Star> stars = starMapper.findByStarIdAndtypeandUserId(momentId, 1, uid);
        if (stars.size() != 0) {
            return ServerResponse.createBySuccessMessage("已点赞");
        } else {
            Star mStar = new Star();
            mStar.setType(1);
            mStar.setStarId(momentId);
            mStar.setUserId(uid);
            starMapper.insert(mStar);
            return ServerResponse.createBySuccessMessage("点赞成功");
        }
    }

}
