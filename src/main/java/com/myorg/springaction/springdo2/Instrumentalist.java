package com.myorg.springaction.springdo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 15/6/17.
 */
public class Instrumentalist implements Performer{

    private String song;

    //对构造器进行标注b表示当创建bean时即使再spring 配置文件中没有配置
    //<constructor-arg>, 构造器也需要自动装配

    @Autowired
    @Qualifier("saxophone")
    private Instrument instrument;

    public void setSong(String song) {
        this.song = song;
    }

    public String getSong() {
        return song;
    }

    @Override
    public void perform() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Playing"+song+":");
        instrument.play();
    }
}
