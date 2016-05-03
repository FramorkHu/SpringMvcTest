package com.myorg.thread;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 16/5/3.
 */
public class  BoundedBufferTest extends TestCase{


    private static final int LOCKUP_DETECT_TIMEOUT = 2 * 1000;
    public void testIsEmptyWhenConstructed(){
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i=0; i<10; i++){
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    public void testTakeBlockWhenEmpty(){
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(){

            @Override
            public void run() {
                try {
                    int unsafe = bb.take();
                    fail();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            taker.start();
            TimeUnit.MILLISECONDS.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);

            assertFalse(taker.isAlive());
        } catch (Exception e){
            fail();
        }
    }

}
