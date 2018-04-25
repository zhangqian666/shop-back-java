package com.zq.shop.web.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午7:05
 * @Package com.zq.shop.web.service
 **/


public interface IFileService {
    String uploadFile(MultipartFile file, String path);
}
