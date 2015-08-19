package com.myorg.java.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * Created by huyan on 2015/8/18.
 */
public class ChannelTransferTest {

    public static void main(String args[]) throws IOException {

        sameChannelTransferTest();
    }

    public static void sameChannelTransferTest() throws IOException {

        RandomAccessFile fromFile = new RandomAccessFile("E:\\aa.sql", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("E:\\toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long size = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, size);
    }

}
