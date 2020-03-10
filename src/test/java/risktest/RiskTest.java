package risktest;

import cn.com.bosssoft.gep.riskcontrol.core.AbstractStrategyHandler;
import cn.com.bosssoft.gep.riskcontrol.utils.RiskControllerSpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-22
 */
@Slf4j
@SpringBootTest(classes = RiskTest.class)
@RunWith(SpringRunner.class)
public class RiskTest {

    @Test
    public void testStrategy(){

    }

    @Test
    public void testGetBeanById(){
        AbstractStrategyHandler handler = RiskControllerSpringUtils.getStrategyHandler("2");
        System.out.println(handler);
    }
}
