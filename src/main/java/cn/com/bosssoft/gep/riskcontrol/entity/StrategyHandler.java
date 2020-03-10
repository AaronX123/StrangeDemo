package cn.com.bosssoft.gep.riskcontrol.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 策略处理器
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-22
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyHandler {
    private String beanId;
    private String beanClassName;
    public StrategyHandler(String beanId, Class beanClassName) {
        this.beanId = beanId;
        this.beanClassName = beanClassName.getName();
    }

    public void setBeanClassName(Class beanClassName) {
        this.beanClassName = beanClassName.getName();
    }

    public Class getBeanClass() throws ClassNotFoundException {
        return Class.forName(beanClassName);
    }
}
