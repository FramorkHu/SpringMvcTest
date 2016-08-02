package com.myorg.ionetty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by huyan on 2016/7/21.
 */
public class EchoServer {

    private final int port = 8888;

    public static void main(String[] args) throws Exception {
        new EchoServer().start();
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

                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                });

        try {
            ChannelFuture future = server.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
