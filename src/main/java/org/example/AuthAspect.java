package org.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {
    @Pointcut("within(org.example..*)")
    public void authenticatingPointCut() {
        // Pointcut for authentication
    }

    @Pointcut("within(org.example..*)")
    public void authorizationPointCut() {
        // Pointcut for authorization
    }

    @Before("authenticatingPointCut() && authorizationPointCut()")
    public void authenticate() {
        System.out.println("authenticating the request");
    }
}