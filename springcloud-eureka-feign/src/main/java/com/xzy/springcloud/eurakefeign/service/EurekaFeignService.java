package com.xzy.springcloud.eurakefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(value = "eureka-client") // 调用的服务的名称
@FeignClient(value = "EUREKA-CLIENT", fallback = EurekaFeignServiceFailure.class) // 调用的服务的名称 -- 采用熔断器
public interface EurekaFeignService {
    @RequestMapping(value = "/info")
    String getInfo();
}
