package cn.com.bosssoft.gep.riskcontrol.demoweb;

import cn.com.bosssoft.gep.riskcontrol.annotation.EnableRiskControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@EnableRiskControl
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
