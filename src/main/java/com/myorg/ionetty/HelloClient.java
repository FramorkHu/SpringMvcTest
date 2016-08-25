package com.myorg.ionetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by huyan on 2016/8/25.
 */
public class HelloClient {

    public void connect(int port) throws Exception{

        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            Bootstrap client = new Bootstrap();
            client.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .remoteAddress(new InetSocketAddress(port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(new HelloClientHandler());
                        }
                    });

            long start = System.currentTimeMillis();

            ChannelFuture future = client.connect().await();
            ChannelFuture closeFuture = future.channel().closeFuture().await();
            closeFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                    System.out.println("success:"+future.isSuccess()+" "+future.channel().isActive());
                }
            });

            long end = System.currentTimeMillis();
            System.out.println("client Use Time:"+ (end - start));

        } finally {

            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
         new HelloClient().connect(8888);
    }
}
