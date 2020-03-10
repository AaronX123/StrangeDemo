package cn.com.bosssoft.gep.riskcontrol.entity;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2019-11-22
 */
public class JoinPointWrapper<T> {
    private JoinPoint joinPoint;
    /**
     * 传递给下一个链路的额外数据
     */
    private String nextNodeData;
    /**
     * 传递给下一个链路的额外数据类型
     */
    private Class<T> nextNodeDataType;

    public JoinPointWrapper() {
    }

    public JoinPointWrapper(final JoinPoint joinPoint,final String nextNodeData,final Class<T> nextNodeDataType) {
        this.joinPoint = joinPoint;
        this.nextNodeData = nextNodeData;
        this.nextNodeDataType = nextNodeDataType;
    }

    public JoinPointWrapper(final JoinPoint joinPoint,final T nextNodeData,final Class<T> nextNodeDataType){
        this.joinPoint = joinPoint;
        this.nextNodeData = JSON.toJSONString(nextNodeData);
        this.nextNodeDataType = nextNodeDataType;
    }

    public JoinPoint getJoinPoint(){
        return joinPoint;
    }

    public T getNextChainData(){
        return JSON.parseObject(nextNodeData,nextNodeDataType);
    }

    public Class<T> getNextDataType(){
        return nextNodeDataType;
    }


}
