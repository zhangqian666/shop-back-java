package com.zq.shop.web.controller.NormalUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Address;
import com.zq.shop.web.bean.Shipping;
import com.zq.shop.web.service.IShippingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午9:16
 * @Package com.zq.shop.web.controller.NormalUser
 **/

@Api(tags = "收货地址管理")
@RestController
@RequestMapping("/user/shipping")
public class ShippingController {

    @Autowired
    private IShippingService iShippingService;

    @ApiOperation("获取收货地址列表")
    @PostMapping("/list")
    public ServerResponse list(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails) {
        return iShippingService.list(defaultUserDetails.getUid(), 0, 0);
    }

    @ApiOperation("添加收货地址")
    @PostMapping("/add")
    public ServerResponse add(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Shipping shipping) {
        return iShippingService.add(defaultUserDetails.getUid(), shipping);
    }

    @ApiOperation("删除收货地址")
    @PostMapping("/del")
    public ServerResponse del(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer shippingId) {
        return iShippingService.del(defaultUserDetails.getUid(), shippingId);
    }

    @ApiOperation("更新收货地址")
    @PostMapping("/update")
    public ServerResponse update(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Shipping shipping) {
        return iShippingService.update(defaultUserDetails.getUid(), shipping);
    }

    @ApiOperation("选择默认收货地址")
    @PostMapping("/select")
    public ServerResponse<Shipping> select(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, Integer shippingId) {
        return iShippingService.select(defaultUserDetails.getUid(), shippingId);
    }

    @ApiOperation("获取地址选择信息")
    @PostMapping("/address")
    public ServerResponse<List<Address>> address(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails, String shengCode, String diCode, Integer level) {
        return iShippingService.address(defaultUserDetails.getUid(), shengCode, diCode, level);
    }


}
