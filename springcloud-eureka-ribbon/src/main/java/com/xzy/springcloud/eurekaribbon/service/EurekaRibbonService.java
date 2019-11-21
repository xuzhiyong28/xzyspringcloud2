package com.xzy.springcloud.eurekaribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class EurekaRibbonService {
    @Autowired
    private RestTemplate restTemplate;

    public String getInfo() {
        String message;
        try {
            System.out.println("调用 服务 EUREKA-CLIENT/info");
            message = restTemplate.getForObject("http://EUREKA-CLIENT/info", String.class);
            System.out.println("服务 EUREKA-CLIENT/info 返回信息 : " + message);
            System.out.println("调用 服务 EUREKA-CLIENT/info 成功！");
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        return message;
    }

    /***
     * http://itindex.net/detail/57782-hystrix-%E7%86%94%E6%96%AD%E5%99%A8-%E6%8A%80%E6%9C%AF
     * circuitBreaker.errorThresholdPercentage :
     *              设定错误百分比，默认值50%，例如一段时间（10s）内有100个请求，其中有55个超时或者异常返回了，
     *              那么这段时间内的错误百分比是55%，大于了默认值50%，这种情况下触发熔断器-打开
     * circuitBreaker.requestVolumeThreshold:
     *              默认20，意思至少有20个请求才进行errorThresholdPercentage错误百分比计算
     *              比如一段时间（10s）内有19个请求全部失败了。错误百分比是100%，但熔断器不会打开，因为requestVolumeThreshold的值是20
     * @return
     */
    @HystrixCommand(fallbackMethod = "getInfoFailure" , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"), //默认超时时间设置
            @HystrixProperty(name = "execution.timeout.enabled" , value = "true"),
            //10s内如果有10个请求进来，如果有10%(1个)超时了，且下面5表示，至少10秒内有5个请求进来的时候才进行判断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "10"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "5000")
    })
    public String hystrixGetInfo(){
        String message;
        try {
            System.out.println("调用 服务 EUREKA-CLIENT/info");
            message = restTemplate.getForObject("http://EUREKA-CLIENT/info", String.class);
            System.out.println("服务 EUREKA-CLIENT/info 返回信息 : " + message);
            System.out.println("调用 服务 EUREKA-CLIENT/info 成功！");
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        return message;
    }

    //@HystrixCommand(fallbackMethod = "getInfoFailure")
    public Future<String> hystrixGetInfo2(){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://EUREKA-CLIENT/info", String.class);
            }
        };
    }


    /**
     * 服务 EUREKA-PROVIDER/hello 调用失败的回调方法
     * 参数 id , throwable 也可以不加
     * @return
     */
    @HystrixCommand(fallbackMethod = "getInfoFailure")
    public String getInfoFailure() {
        String message = "网络繁忙，请稍后再试-_-。PS：服务消费者自己提供的信息";
        return message;
    }
}
