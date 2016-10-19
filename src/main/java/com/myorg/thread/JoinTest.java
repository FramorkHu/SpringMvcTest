package com.myorg.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 16/10/12.
 */
public class JoinTest implements Runnable{

    public static int a = 0;

    public void run() {
        for (int k = 0; k < 1000000; k++) {
            /*if (k%110 == 0){
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            a = a + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(a);
    }
}
