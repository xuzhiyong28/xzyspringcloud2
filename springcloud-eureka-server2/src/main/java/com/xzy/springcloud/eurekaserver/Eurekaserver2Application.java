package com.xzy.springcloud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 启用 eureka server 相关默认配置
public class Eurekaserver2Application {

	public static void main(String[] args) {
		SpringApplication.run(Eurekaserver2Application.class, args);
	}

}
