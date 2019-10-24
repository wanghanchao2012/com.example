package com.mrwang.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class WebServer {
	public static void main(String[] args) {
		try {
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
			ssc.configureBlocking(false);

			Selector selector = Selector.open();
			// 注册 channel，并且指定感兴趣的事件是 Accept
			ssc.register(selector, SelectionKey.OP_ACCEPT);

			ByteBuffer readBuff = ByteBuffer.allocate(1024);
			ByteBuffer writeBuff = ByteBuffer.allocate(128);
			writeBuff.put("received".getBytes());
			writeBuff.flip();

			while (true) {
				int nReady = selector.select();
				if (nReady <= 0) {
					continue;
				}
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();

				while (it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();

					if (key.isAcceptable()) {
						System.out.println("11111111111");
						// 创建新的连接，并且把连接注册到selector上，而且，
						// 声明这个channel只对读操作感兴趣。
						SocketChannel socketChannel = ssc.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						System.out.println("222222222");

						SocketChannel socketChannel = (SocketChannel) key.channel();
						readBuff.clear();
						socketChannel.read(readBuff);

						readBuff.flip();
						System.out.println("received : " + new String(readBuff.array()));
						key.interestOps(SelectionKey.OP_WRITE);
					} else if (key.isWritable()) {
						System.out.println("333333");

						writeBuff.rewind();
						SocketChannel socketChannel = (SocketChannel) key.channel();
						socketChannel.write(writeBuff);
						key.interestOps(SelectionKey.OP_READ);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}