package com.coffeemachine.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @AfterThrowing(pointcut = "execution(* com.coffeemachine..*(..)) && !within(com.coffeemachine.api..*)", throwing = "throwable")
    public void logAfterThrowing(Throwable throwable){
        StringBuilder traceBuilder = new StringBuilder();
        Arrays.stream(throwable.getStackTrace()).forEach(traceBuilder::append);
        String trace = traceBuilder.toString();
        log.error("Error: {}, trace: {}", throwable.getMessage(), trace);
    }

}
