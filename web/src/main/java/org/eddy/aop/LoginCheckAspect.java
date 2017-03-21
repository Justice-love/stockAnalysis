package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/3/21.
 */
@Aspect
@Component
public class LoginCheckAspect {

    @Pointcut("execution(* org.eddy.web..*(..)) && (@annotation(org.eddy.annotation.LoginCheck))")
    public void loginCheckPointcut(){}

    @Around("loginCheckPointcut()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
        return point.proceed();
    }
}
