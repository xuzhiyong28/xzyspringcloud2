package com.xzy.springcloud.eurakezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class EurakezuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurakezuulApplication.class, args);
	}

}
