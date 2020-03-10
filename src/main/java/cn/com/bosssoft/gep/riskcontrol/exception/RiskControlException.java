package cn.com.bosssoft.gep.riskcontrol.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
public class RiskControlException extends RuntimeException implements CommonException{
    private String errorCode;
    private String errorMessage;

    public RiskControlException(){
        super();
    }
    public RiskControlException(String errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public RiskControlException(CommonException ex){
        super(ex.getErrorMessage());
        this.errorCode = ex.getErrorCode();
        this.errorMessage = ex.getErrorMessage();
    }

    public RiskControlException(CommonException ex,Object... args){
        super(String.format(ex.getErrorMessage(),args));
        this.errorCode = ex.getErrorCode();
        this.errorMessage = String.format(ex.getErrorMessage(),args);
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
    public CommonException setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
