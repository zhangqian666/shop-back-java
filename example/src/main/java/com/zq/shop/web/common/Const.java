package com.zq.shop.web.common;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午2:41
 * @Package com.zq.shop.web.common
 **/


public class Const {

    public interface OrderStatus {
        int CANCELED = 0;//"已取消"),

        int NO_PAY = 10;//"未支付"),

        int PAID = 20;//"已付款"),

        int SHIPPED = 40;//"已发货"),

        int ORDER_SUCCESS = 50;//"订单完成"),

        int ORDER_CLOSE = 60;//"订单关闭");
    }

    public interface Cart {
        int CHECKED = 1;
        int UN_CHECK = 0;
    }


    public interface Role {
        int CUSTOME_USER = 2;
        int SUERPER_ADMIN = 0;
        int SHOP = 1;
    }

    public interface User {
        String EMAIL = "email";
        String PHONE = "phone";
        String IMAGE_PER = "http://zack-image.oss-cn-beijing.aliyuncs.com/";
        String DEFAULT_NAME = "User_";
    }

    public interface ProductStatus {
        int ON_SALE = 1;
    }

    public interface PaymentType {
        int ON_Line = 0;
        int ON_SHOP = 1;
    }


    public interface IDType {
        String USER_ID = "user_id";
        String PRODUCT_ID = "product_id";
        String ORDER_ID = "order_id";
        String ORDER_SETTLEMENTS_ID = "order_settlements_id";
        String CATEGORY_ID = "category_id";
        String PAY_INFO_ID = "pay_info_id";
        String SHIPPING_ID = "shipping_id";
        String MOMENTS_ID = "moments_id";
        String MOMENTS_COMMENT_ID = "moments_comment_id";
    }
}
