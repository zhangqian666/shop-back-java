package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Product;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午2:05
 * @Package com.zq.shop.web.service
 **/


public interface IProductService {
    ServerResponse getProductListByKeywordCategory(String keyword, Integer categoryId, int pagenum, int pagesize, String orderby);

    ServerResponse details(Integer productId);

    ServerResponse saveOrUpdateProduct(Product product,Integer userId);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);


    ServerResponse<List<Product>> getProductList(int pageNum, int pageSize);

    ServerResponse<List<Product>> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

}
