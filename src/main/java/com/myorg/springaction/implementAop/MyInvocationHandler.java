package com.myorg.springaction.implementAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huyan on 2015/8/14.
 */
public class MyInvocationHandler implements InvocationHandler {

    Object myProxy;

    public MyInvocationHandler(Object myProxy){

        this.myProxy = myProxy;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before do something");
        Object object = method.invoke(myProxy, args);

        return object;
    }
}
