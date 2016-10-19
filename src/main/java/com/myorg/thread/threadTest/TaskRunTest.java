package com.myorg.thread.threadTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 16/10/12.
 */
public class TaskRunTest {


    public static void main(String[] args) throws InterruptedException {

        try {
            System.out.println("size"+PrimeGenerator.aSecondOfPrimes().size());


            //TimedRun1.timedRun(new PrimeGenerator(), 2000, TimeUnit.MILLISECONDS);

            //TimedRun2.timedRun(new PrimeGenerator(), 100, TimeUnit.MILLISECONDS);
            //System.out.println("activeCount:"+Thread.activeCount());
        } catch (NullPointerException e){
            System.out.println("catch nullPointException" + e);
        }



    }

}
