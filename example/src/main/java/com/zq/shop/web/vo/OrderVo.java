package com.zq.shop.web.vo;

import com.zq.shop.web.bean.Order;
import com.zq.shop.web.bean.OrderItem;
import com.zq.shop.web.bean.Shipping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午8:41
 * @Package com.zq.shop.web
 **/

@Getter
@Setter
public class OrderVo {
    private Order order;
    private List<OrderItem> orderItems;
    private Shipping shipping;
}
