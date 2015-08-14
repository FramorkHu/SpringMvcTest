package com.myorg.springaction.springdo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyan on 15/6/18.
 */
public class Test2 {
    public static void main(String args[]) throws InterruptedException {


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/Application-springdo2.xml");


        Performer kenny = (Performer)context.getBean("kenny");

        kenny.perform();

        Thinker volunteer = (Thinker)context.getBean("volunteer");
        MindReader magician = (MindReader)context.getBean("magician");
        volunteer.thinkOfSomething("what am i thinking");
        System.out.println(magician.getThought());
    }
}
