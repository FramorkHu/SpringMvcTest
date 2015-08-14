package com.myorg.springaction.springdo1;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by huyan on 15/6/21.
 */
public class Audience {

    public void takeSeats(){
        System.out.println("The Audience is taking their seat");
    }

    public void turnOffCallPhone(){
        System.out.println("The audience is turn off the phone");
    }

    public void applaud(){
        System.out.println("clap clap clap clap");
    }

    public void demandRefund(){
        System.out.println("Boo! We want our money back!");
    }

    public void watchPerformance(ProceedingJoinPoint joinPoint){
        try {

            System.out.println("The Audience is taking their seat");
            System.out.println("The audience is turn off the phone");
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            System.out.println("clap clap clap clap");
            System.out.println("The performance has taking "+(end- start)+"millis");
        } catch (Throwable e){
            e.printStackTrace();
        }
    }
}
