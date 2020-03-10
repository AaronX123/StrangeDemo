package cn.com.bosssoft.gep.riskcontrol.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
public enum RiskException implements CommonException {
    /**
     * 系统异常，需要先log.error记录下真正的异常然后再抛出。避免以后定位异常困难
     */
    SYSTEM_ERROR("000001","系统异常"),
    /**
     * 非法访问
     */
    INVALID_ACCESS("000002","非法访问"),
    BEAN_ID_CANNOT_BE_SAME("000003","已存在此处理器ID，请更换"),
    RISK_MESSAGE("000004","违反[%s]，存在风险"),
    ACQUIRE_HANDLER_FAIL("000005","获取[%s]handler失败"),
    ;

    private String errorCode;
    private String errorMessage;

    RiskException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 动态的更改异常信息
     *
     * @param errorMessage
     */
    @Override
    public CommonException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public CommonException setErrorCode(String code) {
        this.errorCode = code;
        return this;
    }
}
