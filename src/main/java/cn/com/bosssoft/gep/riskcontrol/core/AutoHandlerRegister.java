package cn.com.bosssoft.gep.riskcontrol.core;

import cn.com.bosssoft.gep.riskcontrol.entity.Strategy;
import cn.com.bosssoft.gep.riskcontrol.entity.StrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.handler.TestHandler1;
import cn.com.bosssoft.gep.riskcontrol.handler.TestHandler2;
import cn.com.bosssoft.gep.riskcontrol.handler.TestHandler3;
import cn.com.bosssoft.gep.riskcontrol.utils.RiskControllerSpringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-22
 */
@Service
public class AutoHandlerRegister {
    /**
     * 策略缓存，减少每次访问数据库获取策略
     */
    private Map<String, Strategy> strategyCache = new ConcurrentHashMap<>(16);

    /**
     * 根据策略名来配置策略处理器的执行顺序
     * @param strategyName 策略名
     * @return 配置完成后的策略处理器链表头节点
     */
    AbstractStrategyHandler createHandlerListByStrategyName(String strategyName){
        Strategy strategy = strategyCache.get(strategyName);
        if (strategy == null){
            //查数据库然后生成strategy放进去
            //模拟查询并解析的过程
            if ("test".equals(strategyName)){
                List<StrategyHandler> strategies = new ArrayList<>();
                strategies.add(new StrategyHandler("1", TestHandler1.class));
                strategies.add(new StrategyHandler("3", TestHandler3.class));
                strategies.add(new StrategyHandler("2", TestHandler2.class));
                strategy = new Strategy(strategyName,strategies);
                strategyCache.put(strategyName,strategy);
                return register(strategies);
            }
        }else {
            List<StrategyHandler> strategyHandlerList = strategy.getHandlerList();
            return register(strategyHandlerList);
        }
        return null;
    }

    private AbstractStrategyHandler register(List<StrategyHandler> strategyHandlerList){
        StrategyHandler headStrategyHandler = strategyHandlerList.get(0);
        AbstractStrategyHandler head = RiskControllerSpringUtils.getHandlerInstance(headStrategyHandler.getBeanClassName());
        AbstractStrategyHandler temp = head;
        for (StrategyHandler strategyHandler : strategyHandlerList) {
            if (!headStrategyHandler.equals(strategyHandler)){
                temp.nextNode = RiskControllerSpringUtils.getHandlerInstance(strategyHandler.getBeanClassName());
                temp = temp.nextNode;
            }
        }
        return head;
    }
}
