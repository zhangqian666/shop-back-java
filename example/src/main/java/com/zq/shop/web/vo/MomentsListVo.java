package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/2 下午3:28
 * @Package com.zq.shop.web.vo
 **/

@Getter
@Setter
public class MomentsListVo {
    private MomentVo momentVo;
    private List<MomentCommentVo> momentCommentVos;
}
