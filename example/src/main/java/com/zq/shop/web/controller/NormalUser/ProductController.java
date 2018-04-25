package com.zq.shop.web.controller.NormalUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午1:59
 * @Package com.zq.shop.web.controller
 **/


@Api(tags = "商品管理")
@RestController
@RequestMapping("/user/product")
public class ProductController {


    @Autowired
    private IProductService iProductService;

    @ApiOperation("商品列表，按条件")
    @GetMapping("/list")
    public ServerResponse list(@RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "categoryId", required = false) Integer categoryId,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductListByKeywordCategory(keyword, categoryId, pageNum, pageNum, orderBy);

    }


    @ApiOperation("商品详情")
    @ApiImplicitParam(value = "产品id", name = "productId")
    @PostMapping("/details")
    public ServerResponse details(Integer productId) {
        return iProductService.details(productId);
    }
}
