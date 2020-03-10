package cn.com.bosssoft.gep.riskcontrol.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Strategy {
    /**
     * 具体策略名称
     */
    String strategyName;
    List<StrategyHandler> handlerList;
}
