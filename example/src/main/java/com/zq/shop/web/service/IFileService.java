package com.zq.shop.web.service;

import com.zq.core.restful.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午7:05
 * @Package com.zq.shop.web.service
 **/


public interface IFileService {
    ServerResponse<String> uploadFile(MultipartFile file, String path);

    ServerResponse<List<String>> uploadFiles(List<MultipartFile> files, String path);
}
