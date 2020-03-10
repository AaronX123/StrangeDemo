# 风险控制DEMO
<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

## 使用说明
### 1.基于Spring AOP编写。所以在Spring Boot 2环境下，需要启用如下注解

@EnableRiskControl

### 2.编写具体的风险控制策略处理器(handler)

1.首先编写具体的风险控制策略处理器(handler)，需要继承AbstractStrategyHandler，实现handle()方法。并且加上@Service注解。在整个方法中能够获取到请求的所有数据。要选择handler的执行顺序，只需要在handle()方法中顺序的调用register()方法。
先注册的类会在本类执行后先执行。

例：HandlerA.handler(){ register(HandlerB); register(HandlerC);}
则执行顺序为HandlerA -> HandlerB -> HandlerC.

2.请为每个handler设置一个BeanId和strategyContent, BeanId请不要和其它策略处理器重复，否则不起效。

例：

    public IPLimitHandler(){
        this.beanId = "555";
        this.strategyContent = "限制IP访问次数";
    }


### 3.编写RiskControlException处理器


    @ExceptionHandler(RiskControlException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public CommonError handleRiskException(RiskControlException e){
        log.error("风险：{}",e.getErrorMessage(),e);
        return new CommonError(e.getErrorCode(),e.getErrorMessage());
    }
里面的CommonError是业务系统的通用异常。

### 4.启用功能
在具体要进行控制的方法上添加@RiskController注解，该注解有2个参数，

        /**
         * 说明被注解的方法的策略需要什么类来操作
         */
        Class<? extends AbstractStrategyHandler>[] handlers() default DefaultStrategyHandler.class;
    
        /**
         * 策略名
         */
        String strategyName() default "null";
   
如果只填写handlers，则执行顺序依据编写的handler决定。如果填写了strategyName，则会屏蔽handlers的执行顺序，转而从数据库中获取执行策略。如果什么都不填则不做处理。