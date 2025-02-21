package org.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAscept {
    @Before("execution(* org.example.Shopping.checkout(..))")// the 2 dots are for passing all parameter in the function to here
    public void logger(){
        System.out.println("loggers");
    }

    @After("execution(* org.example.Shopping.checkout(..))")
    public void afterLogger(){
        System.out.println("after logger");
    }
}
