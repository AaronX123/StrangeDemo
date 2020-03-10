package cn.com.bosssoft.gep.riskcontrol.core;

import cn.com.bosssoft.gep.common.utils.CommonUtils;
import cn.com.bosssoft.gep.riskcontrol.entity.JoinPointWrapper;
import org.aspectj.lang.JoinPoint;
import cn.com.bosssoft.gep.riskcontrol.utils.RiskControllerSpringUtils;

import java.util.List;

/**
 * 抽象策略处理器,实现类需要加上@Service注解或@Component用于获取bean实例
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
public abstract class AbstractStrategyHandler {

    /**
     * handler代表的策略内容,当报错时直接显示"违反 + strategyContent"
     */
    protected String strategyContent;
    protected String beanId;
    protected AbstractStrategyHandler nextNode;
    /**
     * @param joinPoint
     * @return
     */
    public abstract JoinPointWrapper handle(JoinPointWrapper joinPoint);

    /**
     * 此方法采用递归处理，调用时应调用此方法。
     * @param joinPoint
     * @return
     */
    void check(JoinPoint joinPoint, String strategyName){
        JoinPointWrapper joinPointWrapper = new JoinPointWrapper(joinPoint,null,null);
        AutoHandlerRegister register = RiskControllerSpringUtils.getBean(AutoHandlerRegister.class);
        this.nextNode = register.createHandlerListByStrategyName(strategyName);
        check0(joinPointWrapper);
    }

    private void check0(JoinPointWrapper joinPointWrapper){
        JoinPointWrapper joinPointWrapper1 = this.handle(joinPointWrapper);
        if (this.nextNode == null){
            return;
        }
        this.nextNode.check0(joinPointWrapper1);
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getStrategyContent() {
        return strategyContent;
    }

    public void setStrategyContent(String strategyContent) {
        this.strategyContent = strategyContent;
    }

    /**
     * 将指定类实例注册到本身nextNode上
     * @param handler
     */
    public void register(Class<? extends AbstractStrategyHandler> handler){
        AbstractStrategyHandler strategyHandlerNode = RiskControllerSpringUtils.getBean(handler);
        if (this.nextNode != null){
            return;
        }
        this.nextNode = strategyHandlerNode;
    }

    /**
     * 根据list中的顺序进行注册
     * @param strategyHandlerClassList
     */
    public void register(List<Class<? extends AbstractStrategyHandler>> strategyHandlerClassList){
        if (CommonUtils.isEmpty(strategyHandlerClassList)){
            return;
        }
        AbstractStrategyHandler head = this;
        for (Class<? extends AbstractStrategyHandler> strategyHandlerClass : strategyHandlerClassList) {
            AbstractStrategyHandler node = RiskControllerSpringUtils.getBean(strategyHandlerClass);
            if(head.nextNode == null && node != head){
                head.nextNode = node;
                head = node;
            }
        }
    }

}
