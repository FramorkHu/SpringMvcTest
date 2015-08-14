package com.myorg.springaction.springdo1;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by huyan on 15/6/17.
 */
public class Auditorium{

    public void turnOnLights(){
        System.out.println("turn on lights11111111");
    }

    public void turnOffLights(){
        System.out.println("turn off lights");
    }

}
