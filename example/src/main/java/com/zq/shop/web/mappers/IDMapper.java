package com.zq.shop.web.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/26 下午3:54
 * @Package com.zq.shop.web.mappers
 **/


public interface IDMapper {
    /**
     * @param typeId
     * @return
     */
    Integer findId(@Param("type_id") String typeId);

}
