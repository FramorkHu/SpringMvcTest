package com.myorg.rcp.rmi;

/**
 * Created by huyan on 16/1/7.
 */
public class SpitterServiceImpl implements SpitterService {
    @Override
    public String doSpitter() {
        System.out.println("do invok");
        return "invoker spitter Service";
    }
}
