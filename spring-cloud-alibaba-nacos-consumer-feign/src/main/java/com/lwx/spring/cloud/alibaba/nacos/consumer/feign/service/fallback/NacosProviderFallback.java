package com.lwx.spring.cloud.alibaba.nacos.consumer.feign.service.fallback;

import com.lwx.spring.cloud.alibaba.nacos.consumer.feign.service.NacosProviderService;
import org.springframework.stereotype.Component;

/**
 * @author Liuwuxiang
 * @date 2022年03月10日 23:09
 */
@Component
public class NacosProviderFallback implements NacosProviderService {
    @Override
    public String echo(String message) {
        return "服务断开，请检查您的网络！";
    }
}
