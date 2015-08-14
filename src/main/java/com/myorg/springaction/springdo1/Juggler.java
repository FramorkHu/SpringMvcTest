package com.myorg.springaction.springdo1;

/**
 * Created by huyan on 15/6/16.
 */
public class Juggler implements Performer {

    private int beanBags = 3;
    private String name = "test";

    public Juggler(){

    }

    public Juggler(int beanBags, String name){

        this.beanBags = beanBags;
        this.name = name;
    }

    @Override
    public void perform() {
        System.out.println("Juggler "+beanBags+" Beanbags "+ name);
    }


}
