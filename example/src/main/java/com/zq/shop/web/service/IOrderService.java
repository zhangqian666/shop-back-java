package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Order;
import com.zq.shop.web.vo.OrderVo;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午7:59
 * @Package com.zq.shop.web.service
 **/


public interface IOrderService {
    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse<String> cancel(Integer userId, Long orderNo);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<List<Order>> getOrderList(Integer userId, int pageNum, int pageSize);

    ServerResponse getOrderCheckedProductList(Integer uid, int i, int i1);

    //manage
    ServerResponse<List<OrderVo>> manageList(int pageNum, int pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<String> manageSendGoods(Long orderNo);
}
