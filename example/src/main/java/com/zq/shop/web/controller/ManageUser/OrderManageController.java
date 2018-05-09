package com.zq.shop.web.controller.ManageUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午2:42
 * @Package com.zq.shop.web.controller.ManageUser
 **/

@Api(tags = "管理员：订单管理")
@RestController
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation("列表")
    @PostMapping("/list")
    public ServerResponse manageList() {
        return iOrderService.manageList(0, 0);
    }

    @ApiOperation("订单详情")
    @PostMapping("/details")
    public ServerResponse manageDetails(Long orderNo) {
        return iOrderService.manageDetail(orderNo);
    }

    @ApiOperation("发送货物")
    @PostMapping("/send_goods")
    public ServerResponse manageSendGoods(Long orderNo) {
        return iOrderService.manageSendGoods(orderNo);
    }
}
