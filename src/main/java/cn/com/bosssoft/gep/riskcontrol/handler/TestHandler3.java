package cn.com.bosssoft.gep.riskcontrol.handler;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.entity.JoinPointWrapper;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-22
 */
@Service
public class TestHandler3 extends AbstractStrategyHandler {

    public TestHandler3(){
        this.beanId = "3";
        this.strategyContent = "测试策略3";
    }
    /**
     * @param joinPoint
     * @return
     */
    @Override
    public JoinPointWrapper handle(JoinPointWrapper joinPoint) {
        System.out.println("Test 3");
        return joinPoint;
    }
}
