package com.mrwang.example.nio.bianchengsixiang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannal {

	private static final int BSIZE = 1024;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileChannel fc = new FileOutputStream("/Users/wanghanchao/temp_file/data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		fc = new RandomAccessFile("/Users/wanghanchao/temp_file/data.txt", "rw").getChannel();
		System.out.println(fc.size());
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		fc = new FileInputStream("/Users/wanghanchao/temp_file/data.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		System.out.println(buffer.position());
		fc.read(buffer);
		System.out.println(buffer.position());

		buffer.flip();
		System.out.println(buffer.position());
		while (buffer.hasRemaining()) {
			System.out.print((char) buffer.get());
		}

	}

}
