package com.myorg.springaction.implementAop;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * Created by huyan on 2015/8/14.
 */
public class MyFactoryBean implements FactoryBean {

    private String target = null;
    private String interf = null;


    /**
     * 在这个方法中我们通过java的代理技术得到了一个代理类，
     * 所有的参数都是通过XML文件传递给BeanFactory
     * 所以我们需要一个BeanFactory的对象和一个启动运行类
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        //根据className得到class对象
        Class[] interClass = new Class[]{Class.forName(interf)};
        Object objTarget = Class.forName(target).newInstance();

        return Proxy.newProxyInstance(objTarget.getClass().getClassLoader(),interClass, new MyInvocationHandler(objTarget));

    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getInterf() {
        return interf;
    }

    public void setInterf(String interf) {
        this.interf = interf;
    }
}
