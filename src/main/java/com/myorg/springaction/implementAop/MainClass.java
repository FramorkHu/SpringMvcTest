package com.myorg.springaction.implementAop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyan on 2015/8/14.
 */
public class MainClass {

    public static void main(String args[]){

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/Application-implaoptest.xml");

        //Foo foo = (Foo)context.getBean("bean");
        //foo.printMessage("test");

        Foo proxyFoo = (Foo)context.getBean("proxy");
        proxyFoo.printMessage("this is message");
        proxyFoo.printIntegerMessage(10);
    }
}
