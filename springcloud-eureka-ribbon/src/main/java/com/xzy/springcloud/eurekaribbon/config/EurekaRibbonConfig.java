package com.xzy.springcloud.eurekaribbon.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/***
 * 应用配置类，初始化 Bean和配置信息
 */
@Configuration
public class EurekaRibbonConfig {
    @Bean
    @LoadBalanced // 实现负载均衡 --只有加了这个才能使用别名的方式访问服务
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /***
     * 熔断器面板使用
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
