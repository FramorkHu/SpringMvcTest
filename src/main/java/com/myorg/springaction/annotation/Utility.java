package com.myorg.springaction.annotation;

/**
 * Created by huyan on 15/7/30.
 */
@Description(value = "这是一个有用的工具类")
public class Utility {

    @Author(name = "haoran_202",group="com.magc")
    public String work() {
        return "work over!";
    }
}
