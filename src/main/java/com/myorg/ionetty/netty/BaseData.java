package com.myorg.ionetty.netty;

import java.io.Serializable;

/**
 * Created by huyan on 2016/8/3.
 */
public class BaseData {

    String id;

    public BaseData(){

    }
    public BaseData(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
