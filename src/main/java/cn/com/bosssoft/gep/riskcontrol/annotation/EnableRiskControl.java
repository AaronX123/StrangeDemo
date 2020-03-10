package cn.com.bosssoft.gep.riskcontrol.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@ComponentScan(basePackages = {"cn.com.bosssoft.gep.riskcontrol"})
public @interface EnableRiskControl {
}
