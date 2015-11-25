package com.myorg.callback;

/**
 * Created by huyan on 15/11/9.
 */
public class Client implements CSCallBack {

    Server server;

    public Client(Server server){
        this.server = server;
    }

    public void sendMessage(final String msg){

        System.out.println("Client: sent a message to server:"+msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.getClientMessage(Client.this, msg);
            }
        }).start();

        System.out.println("sent message success");
    }

    @Override
    public void process(String status) {
        System.out.println(status);
    }

    public static void main(String args[]){

        new Client(new Server()).sendMessage("test message");
    }
}
