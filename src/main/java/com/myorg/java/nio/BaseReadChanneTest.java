package com.myorg.java.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by huyan on 2015/8/17.
 */
public class BaseReadChanneTest {

    /**
     * 1 写入数据到Buffer
     * 2 调用flip()方法
     * 3 从Buffer中读取数据
     * 4 调用clear()方法或者compact()方法
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws IOException {

        RandomAccessFile file = new RandomAccessFile("E:\\aa.sql","rw");

        FileChannel channel = file.getChannel();

        //设定缓冲区容量
        ByteBuffer buffer = ByteBuffer.allocate(48);
        //buffer中写入数据，记录写入的数据量
        int bytesRead = channel.read(buffer);

        while (bytesRead != -1){
             buffer.flip(); // 准备读取缓冲数据

            while (buffer.hasRemaining()){
                System.out.print( (char)buffer.get());
            }

            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        file.close();
    }

}
