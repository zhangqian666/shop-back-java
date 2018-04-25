package com.zq.shop.web.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.ProductCategory;
import com.zq.shop.web.mappers.ProductCategoryMapper;
import com.zq.shop.web.service.IProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午2:45
 * @Package com.zq.shop.web.service.impl
 **/
@Slf4j
@Service("iProductCategoryService")
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId) {
        Set<ProductCategory> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (ProductCategory categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    private Set<ProductCategory> findChildCategory(Set<ProductCategory> categorySet, Integer categoryId) {
        ProductCategory category = productCategoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<ProductCategory> categoryList = productCategoryMapper.findByParentId(categoryId);
        for (ProductCategory categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }


    public ServerResponse<List<ProductCategory>> getChildrenParallelCategory(Integer categoryId) {
        List<ProductCategory> categoryList = productCategoryMapper.findByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            log.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }


    //==============================================================================================/

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        ProductCategory category = new ProductCategory();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//这个分类是可用的

        int rowCount = productCategoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }


    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        ProductCategory category = new ProductCategory();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = productCategoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    public ServerResponse updateCategoryStatus(Integer categoryId, Boolean status) {
        if (categoryId == null || status != null) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        ProductCategory category = new ProductCategory();
        category.setId(categoryId);
        category.setStatus(status);

        int rowCount = productCategoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类状态成功");
        }
        return ServerResponse.createByErrorMessage("更新品类状态失败");
    }


}
