package cn.com.bosssoft.gep.riskcontrol.handler;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.entity.JoinPointWrapper;
import org.springframework.stereotype.Service;


/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@Service
public class TestHandler2 extends AbstractStrategyHandler {
    public TestHandler2(){
        this.beanId = "2";
        this.strategyContent = "限制商户每日调用次数";
    }
    /**
     * @param joinPointWrapper
     * @return
     */
    @Override
    public JoinPointWrapper handle(JoinPointWrapper joinPointWrapper) {
        System.out.println("Test 2");
        return joinPointWrapper;
    }
}
