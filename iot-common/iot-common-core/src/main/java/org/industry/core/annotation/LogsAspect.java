package org.industry.core.annotation;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogsAspect {

    // 定义切点
    @Pointcut("@annotation(Logs)")
    public void cut() {
    }

    // 切面逻辑, @annotation 与 参数Logs logs 保持一致
    @Around("cut()&&@annotation(logs)")
    public Object doAround(ProceedingJoinPoint joinPoint, Logs logs) throws Throwable {
        String uuid = IdUtil.fastSimpleUUID();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        log.info("Start => [{}].[{}.{}]: {}", uuid, className, methodName, logs.value());
        try {
            Object proceed = joinPoint.proceed();
            log.info("End <= [{}].[{}.{}].[{}ms]: {}", uuid, className, methodName, System.currentTimeMillis() - startTime, logs.value());
            return proceed;
        } catch (Throwable e) {
            log.info("End   <= [{}].[{}.{}].[{}ms]: {}", uuid, className, methodName, System.currentTimeMillis() - startTime, logs.value());
            throw e;
        }
    }
}
