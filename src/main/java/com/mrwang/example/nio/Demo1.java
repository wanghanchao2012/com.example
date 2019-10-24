package com.mrwang.example.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Demo1 {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile aFile = new RandomAccessFile("/Users/wanghanchao/temp_file/123.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(100);
		System.out.println("direct-->" + buf.isDirect());
		System.out.println("limit -->>" + buf.limit());
		int bytesRead = inChannel.read(buf);
		System.out.println("limit -->>" + buf.limit());
		System.out.println("isReadOnly--->" + buf.isReadOnly());
		while (bytesRead != -1) {
			System.out.println("limit -->>" + buf.limit());

			System.out.println();
			System.out.println("Read " + bytesRead);
			buf.flip();
			System.out.println("limit -->>" + buf.limit());
			System.out.println("isReadOnly--->" + buf.isReadOnly());
			System.out.println("direct-->" + buf.isDirect());
			while (buf.hasRemaining()) {
				char c = (char) buf.get();
				System.out.println("----->>" + buf.position());
				if (c == 'f') {
					buf.mark();
					buf.reset();
				}
				System.out.print(c);
			}
			System.out.println("limit -->>" + buf.limit());

			buf.clear();
			System.out.println("limit -->>" + buf.limit());

			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

}
