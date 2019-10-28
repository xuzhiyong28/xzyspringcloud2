package com.xzy.springcloud.eurekaribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix // 启用 hystrix 熔断机制相关配置
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaribbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaribbonApplication.class, args);
	}

}
