package cn.com.bosssoft.gep.riskcontrol.handler;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.demoweb.pojo.Test;
import cn.com.bosssoft.gep.riskcontrol.entity.JoinPointWrapper;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@Service
public class TestHandler1 extends AbstractStrategyHandler {

    public TestHandler1(){
        this.beanId = "1";
        this.strategyContent = "限制商户每日调用";
    }
    /**
     * @param joinPointWrapper
     * @return
     */
    @Override
    public JoinPointWrapper handle(JoinPointWrapper joinPointWrapper) {
        System.out.println("Test 1");

        return new JoinPointWrapper<>(joinPointWrapper.getJoinPoint(),new Test("微信"),Test.class);
    }


}
