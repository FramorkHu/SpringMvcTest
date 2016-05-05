package com.myorg.thread;

import junit.framework.TestCase;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by huyan on 16/5/5.
 */
public class PutTakeTest extends TestCase{


    private static final ExecutorService pool = Executors.newCachedThreadPool();

    private final AtomicLong putSum = new AtomicLong(0);
    private final AtomicLong takSum = new AtomicLong(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nParis;

    PutTakeTest(int capacity, int nParis, int nTrials){

        bb = new BoundedBuffer<>(capacity);
        this.nParis = nParis;
        this.nTrials = nTrials;
        barrier = new CyclicBarrier(nParis*2 +1);
    }

    public static void main(String[] args) {

        new PutTakeTest(10,2, 100000).test();
        pool.shutdown();
    }


    void test(){
        try {

            for (int i=0; i< nParis; i++){
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }

            barrier.await();
            barrier.await();

            System.out.println(takSum.get()+" "+putSum.get());

            assertEquals(takSum.get(), putSum.get());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int xorShift(int data){
        data^= (data << 6);
        data^= (data >>> 21);
        data^= (data << 7);

        return data;
    }


    class Producer implements Runnable {
        @Override
        public void run() {

            try {

                int seed = (this.hashCode() ^ (int)System.nanoTime());
                long sum = 0;

                barrier.await();
                for (int i = nTrials; i>0; --i){
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }

                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            try {

                barrier.await();
                long sum = 0;
                for (int i=nTrials; i>0; --i){
                    sum += bb.take();
                }
                takSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
