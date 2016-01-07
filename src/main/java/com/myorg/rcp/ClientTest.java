package com.myorg.rcp;

import com.myorg.rcp.rmi.SpitterService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyan on 16/1/7.
 */
public class ClientTest {

    public static void main(String args[]){
        String config = MainTest.class.getPackage().getName().replace('.', '/') + "/Application-springrpcClient.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        SpitterService spitterService = (SpitterService) context.getBean("spitterService");
        System.out.println(spitterService.doSpitter());


    }

}
