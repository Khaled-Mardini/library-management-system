package com.maids.libms.main.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.maids.libms.*.*(..))")
    public void logBefore() {
        System.out.println("Before method execution");
    }
}
