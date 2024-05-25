package com.alan.config;

import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.alan.model.entity.AliOssProperties;
import com.alan.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OssConfig {
    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) throws Exception {
        log.info("开始创建阿里云文件上传工具类对象：{}", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getBucketName(),
                aliOssProperties.getFolderName(),
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider());
    }
}
