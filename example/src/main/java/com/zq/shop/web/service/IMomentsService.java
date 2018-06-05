package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Moments;
import com.zq.shop.web.vo.MomentVo;
import com.zq.shop.web.vo.MomentsListVo;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:08
 * @Package com.zq.shop.web.service
 **/


public interface IMomentsService {
    ServerResponse<MomentVo> create(Moments moments, Integer uid);

    ServerResponse<MomentsListVo> details(Integer momentId, Integer uid);

    ServerResponse<List<MomentVo>> list(Integer uid, Integer searchUid);

    ServerResponse star(Integer momentId, Integer uid);
}
