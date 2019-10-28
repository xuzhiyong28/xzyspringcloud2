package com.xzy.springcloud.eurakaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient  // 启用 eureka client 相关默认配置，在 Edgware 以后的版本该注解可以省略
@SpringBootApplication
public class EurakaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurakaclientApplication.class, args);
	}

}
