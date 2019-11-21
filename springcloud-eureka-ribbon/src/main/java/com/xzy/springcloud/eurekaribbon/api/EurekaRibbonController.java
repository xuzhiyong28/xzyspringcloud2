package com.xzy.springcloud.eurekaribbon.api;

import com.google.common.util.concurrent.Futures;
import com.xzy.springcloud.eurekaribbon.service.EurekaRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class EurekaRibbonController {
    @Autowired
    private EurekaRibbonService eurekaRibbonService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/ribbonInfo")
    public String ribbonInfo() {
        String message = eurekaRibbonService.getInfo();
        return "获取的信息:" + message;
    }

    @RequestMapping("/ribbonInfo2")
    public String ribbonInfo2() {
        String message = eurekaRibbonService.hystrixGetInfo();
        return "获取的信息:" + message;
    }

    @RequestMapping("/ribbonInfo3")
    public String ribbonInfo3() throws InterruptedException, ExecutionException, TimeoutException {
        Future<String> messageFuture = eurekaRibbonService.hystrixGetInfo2();
        System.out.println("===========异步执行==========");
        return "获取的信息:" + messageFuture.get(10, TimeUnit.SECONDS);
    }


    @RequestMapping("/ribbonInfo4")
    public String ribbonInfo4(){
        String message  = eurekaRibbonService.hystrixGetInfoError();
        return "获取的信息:" + message;
    }


    @RequestMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
        return instance.getHost() + ":" + instance.getPort();
    }
}
