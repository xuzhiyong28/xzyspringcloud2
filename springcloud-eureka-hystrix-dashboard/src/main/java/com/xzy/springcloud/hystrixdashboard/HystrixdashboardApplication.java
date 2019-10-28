package com.xzy.springcloud.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard // 启用 HystrixDashboard 断路器看板 相关配置
@EnableDiscoveryClient // 启用 Eureka 服务发现 相关配置
@SpringBootApplication
public class HystrixdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixdashboardApplication.class, args);
	}

}
