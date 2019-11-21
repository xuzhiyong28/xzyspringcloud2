package com.xzy.springcloud.eurakaclient.api;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
public class EurekaClientController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/info")
    public String info(HttpServletRequest request) {
        System.out.println("==============阻塞开始================");
        try {
            //30秒延迟
            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(6) );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============阻塞完成================");
        String message = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletPath();
        return message;
    }

    @RequestMapping("/infoError")
    public String infoError(HttpServletRequest request){
        throw new RuntimeException("获取服务信息不可用");
    }
}
