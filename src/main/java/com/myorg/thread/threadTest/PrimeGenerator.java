package com.myorg.thread.threadTest;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;


/**
 * PrimeGenerator
 * <p/>
 * Using a volatile field to hold cancellation state
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
    private static ExecutorService exec = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            1, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),new MyThreadFactory());

    @GuardedBy("this") private final List<BigInteger> primes
            = new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    public void run() {
        BigInteger p = BigInteger.ONE;
        System.out.println("threadName:"+Thread.currentThread().getName());
        while (!Thread.currentThread().isInterrupted()) {
            p = p.nextProbablePrime();
            synchronized (this) {
                if (p.intValue() == 107){
                    throw new NullPointerException();
                }
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try {
            SECONDS.sleep(2);
        } finally {
            generator.cancel();
        }

        return generator.get();
    }
}


class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        System.out.println("catch exception:"+ t.getName()+e.toString());
    }
}

class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        return thread;
    }
}
