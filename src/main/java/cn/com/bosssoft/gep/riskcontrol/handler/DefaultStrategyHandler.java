package cn.com.bosssoft.gep.riskcontrol.handler;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.entity.JoinPointWrapper;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-25
 */
@Service
public class DefaultStrategyHandler extends AbstractStrategyHandler {
    public DefaultStrategyHandler(){
        this.beanId = "0";
        this.strategyContent = null;
    }
    /**
     * @param joinPoint
     * @return
     */
    @Override
    public JoinPointWrapper handle(JoinPointWrapper joinPoint) {
        return joinPoint;
    }
}
