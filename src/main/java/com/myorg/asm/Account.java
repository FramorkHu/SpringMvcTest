package com.myorg.asm;

/**
 * Created by huyan on 2015/9/24.
 */
public class Account {

    public void operate(){
        SecurityChecker.check();
        System.out.println("account operate");
    }
}
