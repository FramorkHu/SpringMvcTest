package com.myorg.callback;

/**
 * Created by huyan on 15/11/9.
 */
public class Server {

    public static void main(String args[]){

    }

    public void getClientMessage(CSCallBack csCallBack, String msg){

        System.out.println("Server : get client message="+msg);

        try {

            Thread.sleep(5000);
        } catch (Exception e){
            e.printStackTrace();
        }

        String status = "200";
        csCallBack.process(status);
    }
}
