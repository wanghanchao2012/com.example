package com.mrwang.example.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.Builder;

public class Main {

	public static void main(String[] args) {
		writeNio();

	}

	private static void readNio() {
		try {
			// 1、开启文件读取流
			FileInputStream fileInputStream = new FileInputStream("/Users/wanghanchao/temp_file/123.txt");

			// 2、获取fileChannel
			FileChannel channel = fileInputStream.getChannel();

			// 3、设置ByteBuffer大小，一次能容纳capacity字节
			int capacity = 900;
			ByteBuffer bf = ByteBuffer.allocate(capacity);

			// 4、当read返回-1时，表示文件读取完毕
			int length = -1;
			while ((length = channel.read(bf)) != -1) {

				byte[] bytes = bf.array();
				System.out.println(new String(bytes, 0, length));

				// 4、将bf position置为0，方便下次读取
				bf.clear();

			}
			channel.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeNio() {
		try {
			// 1、打开文件写入流
			FileOutputStream fileOutputStream = new FileOutputStream("/Users/wanghanchao/temp_file/123.txt");

			// 2、获取fileChannel
			FileChannel channel = fileOutputStream.getChannel();

			// 3、初始化byteBuffer
			String str = "萨达案发生大大sdada34;sdds'";
			System.out.println(str.getBytes().length);
			ByteBuffer bf = ByteBuffer.allocate(1024);

			// 4、将bf position置为0，方便下次读取
			bf.clear();
			System.out.println(bf.position());
			// 5、从byteBuffer的position位置填充byte
			bf.put(str.getBytes());
			System.out.println(bf.position());

			// 6、将bf position置为0，limit设置为position避免写入内容过多
			bf.flip();

			int length = 0;
			System.out.println("00000000000000000000ßå");
			System.out.println(bf.position());
			System.out.println(bf.limit());
			// 7、如果position小于limit即未写入完毕
			while (bf.hasRemaining()) {
				// 8、将buffer内容写入channel
				length = channel.write(bf);
				System.out.println(bf);
			}
			channel.close();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
