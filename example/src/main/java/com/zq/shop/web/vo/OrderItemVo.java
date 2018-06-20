package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/6/19 下午10:41
 * @Package com.zq.shop.web.vo
 **/

@Getter
@Setter
public class OrderItemVo {
    private Long orderNo;
    private Integer userId;
    private Integer productId;
    private String productName;
    private String productImage;
    private BigDecimal currentUnitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private ProductVo productVo;
}
