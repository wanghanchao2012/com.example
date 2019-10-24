package com.mrwang.example.inputstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class InputDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * 流程 1 建立输入输出流 2 绑定输入输出流 3 向缓冲区写数据 4 读取缓冲区数据
		 */
		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream();
		Producer producer = new Producer(out);
		Consumer consumer = new Consumer(in);

		try {
			out.connect(in);
			producer.start();
			consumer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
