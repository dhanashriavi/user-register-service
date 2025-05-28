package com.user.demo.configuration;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.user.demo.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.user.demo.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
    }
}
