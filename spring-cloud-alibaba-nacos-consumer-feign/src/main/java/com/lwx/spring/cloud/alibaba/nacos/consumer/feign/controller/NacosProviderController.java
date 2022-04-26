package com.lwx.spring.cloud.alibaba.nacos.consumer.feign.controller;

import com.lwx.spring.cloud.alibaba.nacos.consumer.feign.service.NacosProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liuwuxiang
 * @date 2022年03月10日 22:32
 */
@RestController
public class NacosProviderController {

    @Qualifier("com.lwx.spring.cloud.alibaba.nacos.consumer.feign.service.NacosProviderService")
    @Autowired
    private NacosProviderService nacosProviderService;

    @GetMapping(value = "echo")
    public String echo(){
        return nacosProviderService.echo("Hello Feign");
    }
}
