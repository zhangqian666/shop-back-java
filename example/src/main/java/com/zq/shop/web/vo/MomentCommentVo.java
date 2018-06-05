package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/14 上午10:58
 * @Package com.zq.shop.web.vo
 **/

@Setter
@Getter
public class MomentCommentVo {
    private Integer id;
    private Integer userId;
    private String username;
    private String userImage;
    private Integer status;
    private Integer momentsId;
    private String content;
    private String images;
}
