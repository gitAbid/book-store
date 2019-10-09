package com.bookstore.book_store.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAdvice {

    Logger logger = LoggerFactory.getLogger(ExecutionTimeAdvice.class);

    @Around("@annotation(com.bookstore.book_store.annotation.Doc)")
    public Object doc(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        logger.info(String.valueOf(time));

        return o;
    }
}
