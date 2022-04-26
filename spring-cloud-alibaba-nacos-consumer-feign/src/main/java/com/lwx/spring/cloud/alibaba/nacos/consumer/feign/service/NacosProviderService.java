package com.lwx.spring.cloud.alibaba.nacos.consumer.feign.service;

import com.lwx.spring.cloud.alibaba.nacos.consumer.feign.service.fallback.NacosProviderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Liuwuxiang
 * @date 2022年03月10日 22:29
 */
@FeignClient(value = "nacos-provider", fallback = NacosProviderFallback.class)
public interface NacosProviderService {

    @GetMapping("echo/{message}")
    public String echo(@PathVariable(value = "message") String message);
}
