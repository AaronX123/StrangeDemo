package cn.com.bosssoft.gep.riskcontrol.demoweb.controller;

import cn.com.bosssoft.gep.riskcontrol.annotation.RiskController;
import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.handler.TestHandler1;
import cn.com.bosssoft.gep.riskcontrol.handler.TestHandler2;
import cn.com.bosssoft.gep.riskcontrol.handler.TestHandler3;
import cn.com.bosssoft.gep.riskcontrol.utils.RiskControllerSpringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@RestController
public class DemoController {

    @GetMapping("/hello")
    @RiskController(strategyName = "test")
    public String hello(){
        AbstractStrategyHandler handler = RiskControllerSpringUtils.getStrategyHandler("2");
        System.out.println(handler);
        return "hello Risk Controller";
    }

    @GetMapping("/test")
    public void test() throws ClassNotFoundException {
    }

    public static void main(String[] args) throws InterruptedException {
        long s = System.currentTimeMillis();
        Thread.sleep(1000);
        long s2 = System.currentTimeMillis() - s;
        System.out.println(s2);
    }
}
