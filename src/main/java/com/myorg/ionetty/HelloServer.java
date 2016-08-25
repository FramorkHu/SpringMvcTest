package com.myorg.ionetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


/**
 * Created by huyan on 2016/8/25.
 */
public class HelloServer {


    private final int port = 8888;

    public static void main(String[] args) throws Exception {
        new HelloServer().start();
    }
    public void start() throws Exception{
        ServerBootstrap server = new ServerBootstrap();

        EventLoopGroup group = new NioEventLoopGroup();

        server.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline().addLast(new HelloServerHandler());
                    }
                });

        try {
            long start = System.currentTimeMillis();
            ChannelFuture future = server.bind().sync();
            future.channel().closeFuture().sync();
            long end = System.currentTimeMillis();

            System.out.println("Server use time:"+(end-start));
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
