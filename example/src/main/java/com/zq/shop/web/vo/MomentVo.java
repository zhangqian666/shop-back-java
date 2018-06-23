package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/14 上午10:53
 * @Package com.zq.shop.web.vo
 **/

@Setter
@Getter
public class MomentVo {
    private Integer id;
    private Integer userId;
    private String username;
    private String userImage;
    private Integer category;
    private Integer status;
    private Integer type;
    private String title;
    private String subtitle;
    private String details;
    private String mainImage;
    private String subImages;
    private Date lastSeeTime;
    private Integer star;
    private Integer starEnable;
    private Integer seeTimes;
    private List<MomentCommentVo> momentCommentVoList;
}
