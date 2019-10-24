package com.mrwang.example.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class TestPipe {

	public static Selector getSelector() throws IOException {
		// 获取管道
		Pipe pipe = Pipe.open();

		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put("通过单向管道发送数据".getBytes());
		buf.flip();

		// 将缓冲区中的数据写入管道
		Pipe.SinkChannel sinkChannel = pipe.sink();
		sinkChannel.configureBlocking(false);

		sinkChannel.write(buf);
		Selector selector = Selector.open();
		Set selectedKeys = selector.selectedKeys();

		Iterator keyIterator = selectedKeys.iterator();
		sinkChannel.register(selector, SelectionKey.OP_WRITE);
		while (keyIterator.hasNext()) {

			SelectionKey key = (SelectionKey) keyIterator.next();

			if (key.isAcceptable()) {
				System.out.println("a connection was accepted by a ServerSocketChannel.");
				// a connection was accepted by a ServerSocketChannel.

			} else if (key.isConnectable()) {
				System.out.println("a connection was established with a remote server.");
				// a connection was established with a remote server.

			} else if (key.isReadable()) {
				System.out.println("a channel is ready for reading");
				// a channel is ready for reading

			} else if (key.isWritable()) {
				System.out.println("a channel is ready for writing");
				// a channel is ready for writing

			}

			keyIterator.remove();

		}
		return selector;
	}

	public static void main(String[] args) throws IOException {
		getSelector();
	}
}