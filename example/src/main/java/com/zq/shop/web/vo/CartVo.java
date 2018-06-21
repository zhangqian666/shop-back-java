package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/6/9 下午6:00
 * @Package com.zq.shop.web.vo
 **/


@Getter
@Setter
public class CartVo {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Integer checked;
    private ProductVo productVo;
}
