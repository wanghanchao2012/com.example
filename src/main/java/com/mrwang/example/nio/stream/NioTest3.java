package com.mrwang.example.nio.stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream fileOutputStream = new FileOutputStream("NioTest.txt");
		FileChannel fileChannel = fileOutputStream.getChannel();
		ByteBuffer byeBuffer = ByteBuffer.allocate(512);
		byte[] message = "hello world welcome ,nihao".getBytes();

		for (int i = 0; i < message.length; i++) {
			byeBuffer.put(message[i]);
		}
		byeBuffer.flip();
		fileChannel.write(byeBuffer);
		fileOutputStream.close();
	}

}
