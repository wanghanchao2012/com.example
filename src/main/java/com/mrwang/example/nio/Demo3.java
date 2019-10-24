package com.mrwang.example.nio;

import java.nio.ByteBuffer;

public class Demo3 {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(15); // 15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
		for (int i = 0; i < 10; i++) {
			System.out.println("position-->" + buffer.position());

			buffer.put((byte) i);
			System.out.println("position end-->" + buffer.position());
		}
		System.out.println("position-->" + buffer.position());
		System.out.println("----------");
		buffer.clear();
		System.out.println("【markFunTest】buffer缓冲区中的数据：");
		for (int i = 0; i < buffer.limit(); i++) {
			System.out.println(buffer.get());
		}
		buffer.clear();
		// 使用mark方法进行标记，在位置为4处进行标记
		buffer.position(4);
		/**
		 * ByteBuffer.mark()方法，可以对当前位置进行标记，以后使用ByteBuffer.reset()方法可以使缓冲区的位置重置为以前标记的位置,
		 * 从而可以返回到标记的位置对缓冲区的数据进行操作。
		 */
		buffer.mark();
		System.out.println("标志的位置：" + buffer.position());
		System.out
				.println("【markFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
		buffer.reset(); // 将此缓冲区的位置重置为以前标记的位置
		System.out
				.println("【markFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());

		// 判断在当前位置和上限(最大的位置)之间是否有元素
		boolean isFirst = true;
		while (buffer.hasRemaining()) {
			if (isFirst) {
				System.out.println("【markFunTest】当前位置和上限(最大的位置)之间的数据：");
				isFirst = false;
			}
			System.out.println(buffer.get());
		}
		System.out.println("--------------------");
		// 修改标志的位置的元素值
		System.out.println("===" + buffer.position());
		buffer.reset();
		System.out.println("===" + buffer.position());
		buffer.put((byte) 100);
		System.out.println("===" + buffer.position());
		buffer.clear();
		System.out.println("【markFunTest】buffer缓冲区中的数据：");
		for (int i = 0; i < buffer.limit(); i++) {
			System.out.println(buffer.get());
		}
	}
}
