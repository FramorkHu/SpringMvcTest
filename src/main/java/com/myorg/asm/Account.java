package com.myorg.asm;

/**
 * Created by huyan on 2015/9/24.
 */
public class Account {

    public void operate(){
        System.out.println("account operate");
    }

    public void operate2(int a){
        System.out.println("operate2");
    }

    private int methodReturnInt(){
        System.out.println("methodReturnInt");
        return 1;
    }

    protected String methodString(int a, String tss, SecurityChecker securityChecker){
        System.out.println("methodString is run");
        return "";
    }
}
