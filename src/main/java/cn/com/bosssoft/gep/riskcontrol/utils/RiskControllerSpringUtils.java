package cn.com.bosssoft.gep.riskcontrol.utils;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.exception.NoSuchBeanException;
import cn.com.bosssoft.gep.riskcontrol.exception.RiskControlException;
import cn.com.bosssoft.gep.riskcontrol.exception.RiskException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@Component
public class RiskControllerSpringUtils implements ApplicationContextAware {

    private static volatile ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> target){
        return applicationContext.getBean(target);
    }

    public static AbstractStrategyHandler getHandlerInstance(String className){
        try {
            return (AbstractStrategyHandler) applicationContext.getBean(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RiskControlException(RiskException.ACQUIRE_HANDLER_FAIL,className);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (RiskControllerSpringUtils.applicationContext == null){
            RiskControllerSpringUtils.applicationContext = applicationContext;
        }
    }

    public static AbstractStrategyHandler getStrategyHandler(String beanId){
        Map<String,AbstractStrategyHandler> strategyHandlerMap = applicationContext.getBeansOfType(AbstractStrategyHandler.class);
        for (Map.Entry<String, AbstractStrategyHandler> handlerEntry : strategyHandlerMap.entrySet()) {
            if (handlerEntry.getValue().getBeanId().equals(beanId)){
                return handlerEntry.getValue();
            }
        }
        throw new NoSuchBeanException();
    }
}
