package com.myorg.rcp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyan on 16/1/7.
 */
public class MainTest {

    public static void main(String args[]){
        String config = MainTest.class.getPackage().getName().replace('.', '/') + "/Application-springrpcService.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

    }
}
