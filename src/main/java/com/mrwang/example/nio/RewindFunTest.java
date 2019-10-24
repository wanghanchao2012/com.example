package com.mrwang.example.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class RewindFunTest {
	public static Selector getSelector() throws IOException {
		Selector selector = Selector.open();
		Set selectedKeys = selector.selectedKeys();

		Iterator keyIterator = selectedKeys.iterator();

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ByteBuffer buffer = ByteBuffer.allocate(15); // 15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		for (int i = 0; i < 10; i++) {
			buffer.put((byte) i);
		}
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		buffer.rewind();
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		for (int i = 0; i < 4; i++) {
			buffer.get();
		}
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		buffer.rewind();
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		for (int i = 0; i < 4; i++) {
			buffer.get();
		}
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		System.out.println(buffer.capacity());
		buffer.flip();
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		buffer.rewind();
		System.out.println(
				"【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		System.out.println("【rewindFunTest】buffer缓冲区中的数据：");
		for (int i = 0; i < buffer.limit(); i++) {
			System.out.println(buffer.get());
		}
	}

}
