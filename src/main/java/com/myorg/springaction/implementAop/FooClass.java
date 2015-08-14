package com.myorg.springaction.implementAop;

/**
 * Created by huyan on 2015/8/14.
 */
public class FooClass implements Foo {

    @Override
    public void printMessage(String message) {
        System.out.println(this.getClass().getName() + message);
    }

    @Override
    public void printIntegerMessage(Integer num) {

        System.out.println(this.getClass().getName() + num);
    }

}
