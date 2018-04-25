package com.zq.shop.web.service.impl;

import com.zq.core.restful.ResponseCode;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Product;
import com.zq.shop.web.bean.ProductCategory;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.ProductCategoryMapper;
import com.zq.shop.web.mappers.ProductMapper;
import com.zq.shop.web.service.IProductCategoryService;
import com.zq.shop.web.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午2:05
 * @Package com.zq.shop.web.service.impl
 **/


@Service(value = "iProductService")
public class ProductServiceImpl implements IProductService {


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private IProductCategoryService iProductCategoryService;

    public ServerResponse getProductListByKeywordCategory(String keyword, Integer categoryId, int pagenum, int pagesize, String orderby) {

        if (StringUtils.isBlank(keyword) && categoryId == null) {
            List<Product> products = productMapper.find();
            return ServerResponse.createBySuccess(products);
        }

        List<Integer> categoryIdList = new ArrayList<Integer>();
        if (categoryId != null) {
            ProductCategory category = productCategoryMapper.selectByPrimaryKey(categoryId);
            if (category == null && StringUtils.isBlank(keyword)) {
                return ServerResponse.createBySuccess();
            }
            categoryIdList = iProductCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }

        if (StringUtils.isNotBlank(keyword)) {
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        List<Product> products = productMapper.selectByNameAndCategoryIds(keyword, categoryIdList);

        return ServerResponse.createBySuccess(products);

    }


    public ServerResponse details(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        if (product.getStatus() != Const.ProductStatus.ON_SALE) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        return ServerResponse.createBySuccess(product);
    }

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0) {
                    product.setMainImage(subImageArray[0]);
                }
            }

            if (product.getId() != null) {
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createBySuccess("更新产品失败");
            } else {
                int rowCount = productMapper.insert(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createBySuccess("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");

    }

    @Override
    public ServerResponse<List<Product>> getProductList(int pageNum, int pageSize) {
        return ServerResponse.createBySuccess(productMapper.find());
    }

    @Override
    public ServerResponse<List<Product>> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        return ServerResponse.createBySuccess(productMapper.findByNameLikeOrId(productName, productId));
    }

}
