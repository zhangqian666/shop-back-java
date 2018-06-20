package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/6/19 下午11:05
 * @Package com.zq.shop.web.vo
 **/

@Setter
@Getter
public class OrderShopVo {
    private Integer shopid;
    private String shopname;
    private List<OrderItemVo> orderItemVos;
}
