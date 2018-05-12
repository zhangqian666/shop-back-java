package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/11 上午10:39
 * @Package com.zq.shop.web.vo
 **/
@Getter
@Setter
public class RecommendVo {
    private List<ProductVo> recommendProducts;
    private List<String> recommendImages;
}
