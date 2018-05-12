package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Product;
import com.zq.shop.web.vo.ProductVo;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午2:05
 * @Package com.zq.shop.web.service
 **/


public interface IProductService {
    ServerResponse<List<ProductVo>> getProductListByKeywordCategory(String keyword, Integer categoryId, int pagenum, int pagesize, String orderby);

    ServerResponse<ProductVo> details(Integer productId);

    ServerResponse<ProductVo> saveOrUpdateProduct(Product product, Integer userId);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);


    ServerResponse<List<ProductVo>> getProductList(int pageNum, int pageSize);

    ServerResponse<List<ProductVo>> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

}
