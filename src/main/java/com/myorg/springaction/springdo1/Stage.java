package com.myorg.springaction.springdo1;

/**
 * Created by huyan on 15/6/17.
 */
public class Stage {

    private Stage(){

    }

    private static class StageSingletonHolder{

        static Stage instance = new Stage();
    }

    public static Stage getInstance(){
        return StageSingletonHolder.instance;
    }
}
