package cn.com.bosssoft.gep.riskcontrol.annotation;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.handler.DefaultStrategyHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface RiskController {
    /**
     * 说明被注解的方法的策略需要什么类来操作
     */
    Class<? extends AbstractStrategyHandler>[] handlers() default DefaultStrategyHandler.class;

    /**
     * 策略名
     */
    String strategyName() default "null";
}
