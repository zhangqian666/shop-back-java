package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.MomentsComment;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午4:19
 * @Package com.zq.shop.web.service
 **/


public interface IMomentsCommentService {
    ServerResponse create(MomentsComment momentsComment);
}
