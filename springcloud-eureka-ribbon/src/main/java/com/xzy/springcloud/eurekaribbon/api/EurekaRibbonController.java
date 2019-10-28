package com.xzy.springcloud.eurekaribbon.api;

import com.xzy.springcloud.eurekaribbon.service.EurekaRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaRibbonController {
    @Autowired
    private EurekaRibbonService eurekaRibbonService;

    @RequestMapping("/ribbonInfo")
    public String ribbonInfo() {
        String message = eurekaRibbonService.getInfo();
        return "获取的信息:" + message;
    }

    @RequestMapping("/ribbonInfo2")
    public String ribbonInfo2() {
        String message = eurekaRibbonService.hystrixGetInfo();
        return "获取的信息:" + message;
    }
}
