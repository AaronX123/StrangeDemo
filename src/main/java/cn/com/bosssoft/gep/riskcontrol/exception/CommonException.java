package cn.com.bosssoft.gep.riskcontrol.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-21
 */
public interface CommonException {
    /**
     * 动态的更改异常信息
     * @param errorMessage
     */
    CommonException setErrorMessage(String errorMessage);

    String getErrorMessage();

    String getErrorCode();

    CommonException setErrorCode(String code);
}
