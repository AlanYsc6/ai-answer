//package com.alan.controller;
//
//import com.alan.common.ResultUtils;
//import com.alan.utils.AliOssUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.UUID;
//
///**
// * 通用接口
// */
//@Slf4j
//@Api(tags = "通用接口")
//@RestController
//@RequestMapping("/admin/common")
//public class CommonController {
//    @Autowired
//    private AliOssUtil aliOssUtil;
//
//    //OSS文件上传
//    @ApiOperation("文件上传")
//    @PostMapping("/upload")
//    public ResultUtils<String> upload(MultipartFile file) {
//        log.info("OSS文件上传:{}", file);
//        try {
//            //获取原始文件名
//            String originalFilename = file.getOriginalFilename();
//            //截取原始文件名的后缀
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            //构建新文件名称
//            String objectName = aliOssUtil.getFolderName()+UUID.randomUUID().toString() + extension;
//            //文件请求路径
//            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
//            return ResultUtils.success(filePath);
//        } catch (IOException e) {
//            log.error("文件上传失败:{}",e);
//        }
//        return ResultUtils.error("文件上传失败");
//    }
//}
