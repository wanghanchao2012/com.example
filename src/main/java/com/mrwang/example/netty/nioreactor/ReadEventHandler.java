package com.mrwang.example.netty.nioreactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadEventHandler extends NioEventHandler {

	public ReadEventHandler(Selector selector, SelectionKey key) {
		super(selector, key);
	}

	@Override
	public void handler() throws IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		// 点菜
		ByteBuffer dst = ByteBuffer.allocate(1024);
		int read = socketChannel.read(dst);
		dst.flip();
		String msg = new String(dst.array());
		System.out.println(msg);
		// 你也可以在这里招呼厨房开始做菜
		// TODO mainServer
		// 然后告诉客人菜做好了就会上
		// socketChannel.register(selector, SelectionKey.OP_WRITE);
	}
}