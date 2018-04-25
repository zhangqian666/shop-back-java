package com.zq.shop.web.controller.ManageUser;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.service.IProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午1:59
 * @Package com.zq.shop.web.controller.ManageUser
 **/
@Api(tags = "A管理员：商品类别管理")
@RestController
@RequestMapping("/manage/product/category")
public class ProductCategoryManageController {

    @Autowired
    private IProductCategoryService iProductCategoryService;

    @ApiOperation("添加")
    @PostMapping("/add")
    public ServerResponse add(Integer parentId, String name) {
        return iProductCategoryService.addCategory(name, parentId);
    }

    @ApiOperation("更新名字")
    @PostMapping("/update/name")
    public ServerResponse updateName(Integer categoryId, String name) {
        return iProductCategoryService.updateCategoryName(categoryId, name);
    }

    @ApiOperation("更新状态")
    @PostMapping("/update/status")
    public ServerResponse updateStatus(Integer categoryId, Boolean status) {
        return iProductCategoryService.updateCategoryStatus(categoryId, status);
    }

    @ApiOperation("获取子类别列表")
    @PostMapping("/item/list")
    public ServerResponse itemList(Integer parentId) {
        return iProductCategoryService.getChildrenParallelCategory(parentId);
    }

}
