package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/6/21 下午12:48
 * @Package com.zq.shop.web.vo
 **/

@Getter
@Setter
public class OrderSettlementsVo {
    private Integer id;
    private Integer settlementno;
    private Integer settlementtype;
    private Integer shopid;
    private Long settlementmoney;
    private Long ordermoney;
    private Integer isfinish;
    private Long totalcommission;
    private List<OrderVo> orderVos;
}
