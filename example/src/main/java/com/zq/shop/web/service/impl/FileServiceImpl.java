package com.zq.shop.web.service.impl;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/21 下午7:05
 * @Package com.zq.shop.web.service.impl
 **/
@Slf4j
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    public ServerResponse<String> uploadFile(MultipartFile file, String path) {

        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        try {
            File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.setWritable(true);
                fileDir.mkdirs();
            }
            File targetFile = new File(path, uploadFileName);
            file.transferTo(targetFile);

            aliUpFile(targetFile, uploadFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
//        File fileDir = new File(path);
//        if (!fileDir.exists()) {
//            fileDir.setWritable(true);
//            fileDir.mkdirs();
//        }
//
//        try {
//
//            //文件已经上传成功了
//            if (FTPUtil.uploadFile(Lists.newArrayList(targetFile))) {
//                targetFile.delete();
//                return uploadFileName;
//            } else {
//                targetFile.delete();
//            }
//            //已经上传到ftp服务器上
//            //已经上传到ftp服务器上
//        } catch (IOException e) {
//            log.error("上传文件异常", e);
//            return null;
//        }
        return ServerResponse.createBySuccess(uploadFileName);

    }

    @Override
    public ServerResponse<List<String>> uploadFiles(List<MultipartFile> files, String path) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            urls.add(Const.User.IMAGE_PER + uploadFile(file, path).getData());
        }
        return ServerResponse.createBySuccess(urls);
    }

    private void aliUpFile(File file, String filename) {
        // endpoint以杭州为例，其它region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI0KjgIKFUwRfz";
        String accessKeySecret = "yOWp1p0kuMLAQSCdU5q3NevDea0FXs";
        // 创建OSSClient实例。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret,conf);

        ossClient.putObject("zack-image", filename, file);
        // 关闭client。
        ossClient.shutdown();
    }
}
