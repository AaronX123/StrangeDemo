package cn.com.bosssoft.gep.riskcontrol.exception;

import cn.com.bosssoft.gep.common.entity.CommonError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-22
 */
@Slf4j
@ControllerAdvice(basePackages = "cn.com.bosssoft.gep.riskcontrol")
public class RiskControlExceptionHandler {

    @ExceptionHandler(RiskControlException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public CommonError handleRiskException(RiskControlException e){
        log.error("风险：{}",e.getErrorMessage(),e);
        return new CommonError(e.getErrorCode(),e.getErrorMessage());
    }
}
