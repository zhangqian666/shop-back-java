package com.zq.shop.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/6/12 下午4:22
 * @Package com.zq.shop.web.vo
 **/


@Getter
@Setter
public class CartShopVo {
    private Integer userId;
    private String username;
    private List<CartVo> cartVos;
}
