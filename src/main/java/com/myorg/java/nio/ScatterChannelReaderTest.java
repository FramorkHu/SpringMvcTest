package com.myorg.java.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by huyan on 2015/8/18.
 */
public class ScatterChannelReaderTest {

    public static void main(String args[]) throws IOException {

        RandomAccessFile file = new RandomAccessFile("E:\\aa.sql","rw");
        FileChannel channel = file.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("E:\\niotest\\scatterFile.txt","rw");
        FileChannel toChannel = toFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(100);
        ByteBuffer body = ByteBuffer.allocate(40);

        ByteBuffer[] bufferArray = { header, body };

        long byteRead = channel.read(bufferArray);


        while (byteRead!=-1){

            /*doWrite(header, toChannel);
            doWrite(body, toChannel);*/

            toChannel.write(bufferArray);
            header.clear();
            body.clear();
            byteRead = channel.read(bufferArray);

        }

        file.close();


    }

    public static void doWrite(ByteBuffer buffer,FileChannel toChannel) throws IOException {

        buffer.flip();

        toChannel.write(buffer);

        buffer.clear();
    }
}
