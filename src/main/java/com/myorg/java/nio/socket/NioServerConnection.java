package com.myorg.java.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by huyan on 2016/6/21.
 */
public class NioServerConnection {

    public static int DEFAULT_PORT = 7777;

    public static void main(String[] args) throws IOException {
        System.out.println("Listening for connection on port " + DEFAULT_PORT);

        Selector selector = Selector.open();
        initServer(selector);

        while (true) {
            selector.select();

            for (Iterator<SelectionKey> itor = selector.selectedKeys().iterator(); itor.hasNext();) {
                SelectionKey key = (SelectionKey) itor.next();
                itor.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(selector, SelectionKey.OP_READ);
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int n;
                        while ((n= client.read(buffer))!=-1){
                            System.out.println(new String(buffer.array()));
                        }

                        buffer.flip();
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        System.out.println("is writable...");
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        client.write(buffer);
                        if (buffer.remaining() == 0) {	// write finished, switch to OP_READ
                            buffer.clear();
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                } catch (IOException e) {
                    key.cancel();
                    try { key.channel().close(); } catch (IOException ioe) { }
                }
            }
        }
    }

    private static void initServer(Selector selector) throws IOException,
            ClosedChannelException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket ss = serverChannel.socket();
        ss.bind(new InetSocketAddress(DEFAULT_PORT));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
