package com.myorg.ionetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 2016/6/28.
 */
public class EchoClient {


    public static String host = "127.0.0.1";
    public static int port = 7878;

    /**
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>(){

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            /*
                             * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
                             *
                             * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
                             *
                             * */
                            //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                           /* pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());*/

                            pipeline.addLast("decoder", new MessageToMessageDecoder<Integer>() {

                                @Override
                                protected void decode(ChannelHandlerContext channelHandlerContext, Integer byteBuf, List<Object> list) throws Exception {
                                    list.add(String.valueOf("decode"+byteBuf));
                                }
                            });

                            pipeline.addLast("encoder", new MessageToMessageEncoder<String>() {
                                @Override
                                protected void encode(ChannelHandlerContext channelHandlerContext, String integer, List<Object> list) throws Exception {

                                    list.add(Integer.parseInt(integer));
                                }
                            });

                            // 客户端的逻辑
                            pipeline.addLast("handler", new EchoClientHandler());
                        }
                    });

            // 连接服务端

            /*ExecutorService service = Executors.newFixedThreadPool(10);
            final Channel ch = b.connect(host, port).sync().channel();
            for (int i=0; i<10000; i++){
                final int n = i;
                service.submit(new Runnable() {
                    @Override
                    public void run() {

                        ch.writeAndFlush(n);
                    }
                });

            }

            TimeUnit.SECONDS.sleep(2);
            service.shutdown();*/
            Channel ch = b.connect(host, port).sync().channel();
            // 控制台输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    continue;
                }
                /*
                 * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                 * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                 * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                 * */
                ch.writeAndFlush(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }


   /* private final int port;
    private final String host;

    public EchoClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("192.168.0.106", 8888).start();
    }*/

}
