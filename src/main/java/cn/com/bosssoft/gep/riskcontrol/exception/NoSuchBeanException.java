package cn.com.bosssoft.gep.riskcontrol.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-28
 */
public class NoSuchBeanException extends RiskControlException{
    public NoSuchBeanException(){
        super("0500000","没有这个BeanId对应的策略处理器");
    }
}
