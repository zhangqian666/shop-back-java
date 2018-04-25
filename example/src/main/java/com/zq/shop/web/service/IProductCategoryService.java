package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ProductCategory;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午2:45
 * @Package com.zq.shop.web.service
 **/


public interface IProductCategoryService {
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer id);

    ServerResponse<List<ProductCategory>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse updateCategoryStatus(Integer categoryId, Boolean status);

}
