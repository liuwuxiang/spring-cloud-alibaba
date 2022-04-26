package com.lwx.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Liuwuxiang
 * @date 2022年03月10日 21:37
 */
@RestController
public class NacosConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value(value = "${spring.application.name}")
    private String appName;

    @GetMapping(value = "echo/app/name")
    public String echo(){
        ServiceInstance instance = loadBalancerClient.choose("nacos-provider");
        String s = String.format("http://%s:%s/echo/%s", instance.getHost(), instance.getPort(), appName);
        return restTemplate.getForObject(s, String.class);
    }
}
