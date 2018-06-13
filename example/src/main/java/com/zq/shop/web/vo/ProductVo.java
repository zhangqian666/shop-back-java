package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/11 上午10:44
 * @Package com.zq.shop.web.vo
 **/
@Getter
@Setter
public class ProductVo {

    private Integer id;

    /**
     * Database Column Remarks:
     * 分类id,对应product_category表的主键
     */
    private Integer categoryId;


    private Integer userId;

    private String username;

    /**
     * Database Column Remarks:
     * 商品名称
     */
    @NotBlank(message = "商品名不能为空")
    private String name;


    /**
     * Database Column Remarks:
     * 商品副标题
     */
    private String subtitle;

    /**
     * Database Column Remarks:
     * 产品主图,url相对地址
     */
    private String mainImage;

    /**
     * Database Column Remarks:
     * 价格,单位-元保留两位小数
     */
    private BigDecimal price;

    /**
     * Database Column Remarks:
     * 库存数量
     */
    private Integer stock;

    /**
     * Database Column Remarks:
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

    /**
     * Database Column Remarks:
     * 图片地址,json格式,扩展用
     */
    private String subImages;

    /**
     * Database Column Remarks:
     * 商品详情
     */
    private String detail;

}
