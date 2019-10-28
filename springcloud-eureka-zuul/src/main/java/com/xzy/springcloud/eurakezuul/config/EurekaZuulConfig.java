package com.xzy.springcloud.eurakezuul.config;

import com.xzy.springcloud.eurakezuul.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaZuulConfig {
    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }
}
