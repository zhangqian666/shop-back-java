package com.zq.shop.web.vo;

import com.zq.shop.web.bean.Shipping;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午8:41
 * @Package com.zq.shop.web
 **/

@Getter
@Setter
public class OrderVo {
    private Long orderNo;
    private Integer userId;
    private Integer shippingId;
    private BigDecimal payment;
    private Integer paymentType;
    private Integer settlementId;
    private Integer postage;
    private Integer status;
    private Date paymentTime;
    private Date sendTime;
    private Date endTime;
    private Date closeTime;
    private OrderShopVo orderShopVo;
    private Shipping shipping;
}
