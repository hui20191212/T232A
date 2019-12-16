package com.zking.ssm.annotation.p3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
@Aspect
public class MyLogAspect {

    private static final Logger log = LoggerFactory.getLogger(MyLogAspect.class);

    @Pointcut("@annotation(com.zking.ssm.annotation.p3.MyLog)")
    private void MyValid() {
    }

    @Before("MyValid()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 控制器名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        //操作说明
        MyLog annotation= method.getAnnotation(MyLog.class);
        log.info("控制器名={}",targetName);
        log.info("方法名={}",methodName);
        log.info("操作说明={}",annotation.desc());
    }
}
