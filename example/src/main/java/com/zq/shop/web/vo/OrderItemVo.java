package com.zq.shop.web.vo;

import com.zq.shop.web.bean.OrderItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/6/19 下午10:41
 * @Package com.zq.shop.web.vo
 **/

@Getter
@Setter
public class OrderItemVo {
    private OrderItem orderItem;
    private ProductVo productVo;
}
