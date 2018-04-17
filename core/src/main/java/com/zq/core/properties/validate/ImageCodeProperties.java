package com.zq.core.properties.validate;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:06
 * @Package com.zq.core.properties.validate
 **/

@Getter
@Setter
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int expireIn =60;
    private int length;


    private String url;

    public ImageCodeProperties() {
        setLength(4);
    }
}
