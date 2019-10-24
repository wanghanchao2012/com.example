package com.mrwang.example.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @program: Lear-Java
 * @description:
 * @author: Mr.Dai
 * @create: 2018-10-05 20:43
 **/
public class ThreadSend {

	private Pipe pipe;

	private void init() throws Exception {
		this.pipe = Pipe.open();
	}

	class SendInner1 extends Thread {

		@Override
		public void run() {
			// 单向流 发送数据
			try {
				Pipe.SinkChannel sink = pipe.sink();
				sink.configureBlocking(false);

				while (true) {
					String ss = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,16,17,18,19,20,21,22,23,24,25,25";
					if (sink.isOpen()) {
						sink.write(ByteBuffer.wrap(ss.getBytes()));
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	class ReverInner extends Thread {
		@Override
		public void run() {
			try {
				// 单向流 拿到数据
				Pipe.SourceChannel source = pipe.source();

				source.configureBlocking(false);

				while (true) {
					if (source.isOpen()) {
						ByteBuffer buffer = ByteBuffer.allocate(1000);
						buffer.clear();
						source.read(buffer);
						// 这里必须去掉 trim
						if (new String(buffer.array()).trim().equals("")) {
							continue;
						}
						System.out.println("reactor==" + new String(buffer.array()).trim());
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ThreadSend send = new ThreadSend();

		send.init();

		SendInner1 sendI = send.new SendInner1();

		ReverInner revI = send.new ReverInner();

		sendI.start();
		revI.start();
	}

}