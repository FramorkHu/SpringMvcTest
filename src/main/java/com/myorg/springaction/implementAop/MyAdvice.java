package com.myorg.springaction.implementAop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;


import java.lang.reflect.Method;

/**
 * Created by huyan on 2015/8/14.
 */
public class MyAdvice implements AfterReturningAdvice, MethodBeforeAdvice, MethodInterceptor {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {

        System.out.println("after method running");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        System.out.println("before method running");
    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("use MethodInterceptor before");
        Object object =
                methodInvocation.proceed();
        System.out.println("use MethodInterceptor after");
        return object;
    }
}
