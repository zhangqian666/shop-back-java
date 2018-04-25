package com.zq.shop.web.service.impl;

import com.google.common.collect.Lists;
import com.zq.shop.utils.FTPUtil;
import com.zq.shop.web.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午7:05
 * @Package com.zq.shop.web.service.impl
 **/
@Slf4j
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    public String uploadFile(MultipartFile file, String path) {

        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
            if (FTPUtil.uploadFile(Lists.newArrayList(targetFile))) {
                targetFile.delete();
                return uploadFileName;
            } else {
                targetFile.delete();
            }
            //已经上传到ftp服务器上
            //已经上传到ftp服务器上
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        }
        return uploadFileName;

    }
}
