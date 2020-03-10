package cn.com.bosssoft.gep.riskcontrol.core;

import cn.com.bosssoft.gep.riskcontrol.annotation.RiskController;
import cn.com.bosssoft.gep.riskcontrol.exception.RiskControlException;
import cn.com.bosssoft.gep.riskcontrol.exception.RiskException;
import cn.com.bosssoft.gep.riskcontrol.handler.DefaultStrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import cn.com.bosssoft.gep.riskcontrol.utils.RiskControllerSpringUtils;

/**
 * 切面，在进入真正的业务之前进行拦截处理
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@Slf4j
@Aspect
@Component
public class RiskControllerAspect {
    private String nullStr = "null";

    @Pointcut("@annotation(cn.com.bosssoft.gep.riskcontrol.annotation.RiskController)")
    public void riskControl(){ }

    @Before("riskControl()")
    public void beforeRiskCheck(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info(String.format("请求参数：[%s]",args == null? "null":args));
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RiskController riskController = signature.getMethod().getAnnotation(RiskController.class);
        String strategyName = riskController.strategyName();
        //如果策略为空则根据填写的处理器进行处理
        if (nullStr.equals(strategyName)){
            Class<? extends AbstractStrategyHandler>[] handlers = riskController.handlers();
            for (Class<? extends AbstractStrategyHandler> h : handlers) {
                AbstractStrategyHandler handler = RiskControllerSpringUtils.getBean(h);
                try {
                    handler.check(joinPoint,null);
                }catch (RuntimeException e){
                    if (e instanceof RiskControlException){
                        log.error(String.format("风险控制异常: [%s]",((RiskControlException) e).getErrorMessage()),e);
                        throw new RiskControlException(RiskException.RISK_MESSAGE.setErrorMessage(((RiskControlException) e).getErrorMessage()));
                    }else {
                        log.error("风险控制异常:",e);
                        throw new RiskControlException(RiskException.SYSTEM_ERROR);
                    }
                }
            }
        }else {
            AbstractStrategyHandler handler = RiskControllerSpringUtils.getBean(DefaultStrategyHandler.class);
            handler.check(joinPoint,strategyName);
        }

    }



}
