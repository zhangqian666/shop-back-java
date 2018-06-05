package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/13 下午12:52
 * @Package com.zq.shop.web.vo
 **/

@Setter
@Getter
public class CategoryVo {
    private Integer id;
    private Integer parentId;
    private String name;
    private String image;
    private Boolean status;
    private Integer sortOrder;
    private List<CategoryVo> itemList;
}
