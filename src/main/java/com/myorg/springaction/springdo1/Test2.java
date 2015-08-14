package com.myorg.springaction.springdo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 15/6/18.
 */
public class Test2 {
    public static void main(String args[]) throws InterruptedException {


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/Application-springdo1.xml");

        ApplicationContext context2 = new ClassPathXmlApplicationContext("classpath:spring/Application-springdo1.xml");


        Stage juggler1 = (Stage)context.getBean("stage");
        Stage juggler2 = (Stage)context.getBean("stage");
        Stage juggler3 = (Stage)context2.getBean("stage");
        Stage juggler4 = (Stage)context2.getBean("stage");

        Instrumentalist instrumentalist = (Instrumentalist)context.getBean("kenny");
        instrumentalist.perform();

        Instrumentalist car1 = (Instrumentalist)context.getBean("car1");
        car1.perform();

        TestBvo testBvo = (TestBvo)context.getBean("testBvo");
        System.out.println(testBvo);
        System.out.println(juggler1);
        System.out.println(juggler2);
        System.out.println(juggler3);
        System.out.println(juggler4);

    }
}
