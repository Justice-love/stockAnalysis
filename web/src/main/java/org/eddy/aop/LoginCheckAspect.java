package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/3/21.
 */
@Aspect
@Component
public class LoginCheckAspect {

    public static final String FORBIDDEN = "forbidden";

    @Pointcut("execution(* org.eddy.web..*(..)) && (@annotation(org.eddy.annotation.LoginCheck))")
    public void loginCheckPointcut(){}

    @Around("loginCheckPointcut()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
        if (!loginCheck()) {
            return checkFailAndReturn(point);
        }
        return point.proceed();
    }

    private Object checkFailAndReturn(ProceedingJoinPoint point) throws Throwable {
        if (! MethodSignature.class.isAssignableFrom(point.getSignature().getClass())) {
            return point.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Class r = methodSignature.getReturnType();
        if (r == String.class) {
            return FORBIDDEN;
        } else {
            return point.proceed();
        }
    }

    /**
     *  登陆校验
     * @return true:用户已登陆
     */
    private boolean loginCheck() {
        return true;
    }
}
