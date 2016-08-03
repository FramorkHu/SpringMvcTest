package com.myorg.ionetty.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 2016/7/21.
 */
public class EchoClient {

    private final int port = 8888;


    public static void main(String[] args) throws Exception {

        new EchoClient().start();
        //new EchoClient().test();
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

                        ch.pipeline()
                                .addLast(new EchoClientHandler());
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
                /*future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {

                        System.out.println(future.isSuccess()+" "+future.isDone());
                    }
                });*/
                channel.writeAndFlush(Unpooled.copiedBuffer(encode(new BaseData(line))));

            }


        } finally {
            group.shutdownGracefully().sync();
        }

    }


    public byte[] encode(BaseData baseData){

        String data = JSONObject.toJSONString(baseData);
        return data.getBytes(Charset.forName("UTF-8"));

    }

    public void test(){

        ByteBuf byteBuf = Unpooled.copiedBuffer("string",CharsetUtil.UTF_8);

        if (byteBuf.hasArray()){
            byte[] array = byteBuf.array();
            int offSet = byteBuf.arrayOffset() + byteBuf.readerIndex();
            for (int i=0; i<7; i++){
                byteBuf.readByte();
            }


            int length = byteBuf.readableBytes();

            System.out.println(length);
        }
        System.out.println(byteBuf.hasArray());

    }

    public void compositeByteBuf(){

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.putInt(1);
        buffer.putInt(2);
        buffer.putInt(3);
        buffer.put("aaa".getBytes());
        buffer.get();
    }
}
