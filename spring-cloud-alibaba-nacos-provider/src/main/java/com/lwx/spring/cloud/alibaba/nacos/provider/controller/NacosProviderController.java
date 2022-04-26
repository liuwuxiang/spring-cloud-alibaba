package com.lwx.spring.cloud.alibaba.nacos.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 * @author Liuwuxiang
 */
@RestController
public class NacosProviderController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("echo/{message}")
    public String echo(@PathVariable(value = "message") String message){
        return "Hello Nocos 刘武祥" + message + " I am port " + port;
    }

    @GetMapping(value = "hi")
    public String hi(){
        return applicationContext.getEnvironment().getProperty("user.name");
    }
}
