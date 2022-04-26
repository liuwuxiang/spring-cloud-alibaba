package com.lwx.spring.cloud.alibaba.nacos.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述: 配置类
 *  相当于Java配置类 @Configuration = spring-context.xml <bean id="restTemplate" class="org.springframework.web.client.RestTemplate"></bean>
 * @author: 刘武祥
 * @Date: 2022/4/26 14:23
 */
@Configuration
public class NacosConsumerConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
