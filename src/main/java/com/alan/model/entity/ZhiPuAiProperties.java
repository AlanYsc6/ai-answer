package com.alan.model.entity;

import com.zhipu.oapi.ClientV4;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author Alan
 * @Date 2024/5/28 17:40
 * @Description
 */
@Component
@ConfigurationProperties(prefix = "ai.zhipu")
@Data
public class ZhiPuAiProperties {
    private String apiKey;

    @Bean
    private ClientV4 getClientV4(){
        return new ClientV4.Builder(apiKey).build();
    }
}
