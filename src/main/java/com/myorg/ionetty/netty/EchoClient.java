package com.myorg.ionetty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 2016/7/21.
 */
public class EchoClient {

    private final int port = 8888;


    public static void main(String[] args) throws Exception {

        new EchoClient().start();
    }

    public void start() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap client = new Bootstrap();

        client.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });

        try {
            ChannelFuture future = client.connect().sync();


            Channel channel = future.channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    continue;
                }
                future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {

                        System.out.println(future.isSuccess()+" "+future.isDone());
                    }
                });
                channel.writeAndFlush(Unpooled.copiedBuffer(line, CharsetUtil.UTF_8));

            }


        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
