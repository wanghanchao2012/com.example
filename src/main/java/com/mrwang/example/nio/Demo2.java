package com.mrwang.example.nio;

import java.nio.CharBuffer;

public class Demo2 {
	public static void main(String[] args) {
		putTest();
	}

	/**
	 * put测试以及limit、capacity测试
	 */
	public static void putTest() {
		char[] charArray = new char[] { 'a', 'b', 'c', 'd' };
		CharBuffer buffer = CharBuffer.wrap(charArray);
		// 此时capacity和limit都为4
		System.out.println("CharBuffer.capacity:" + buffer.capacity());
		System.out.println("CharBuffer.limit:" + buffer.limit());
		// 设置limit为3
		buffer.limit(3);
		// 通过put设置数据
		buffer.put('a');
		buffer.put('b');
		buffer.put('c');
		// 下面这一行报错
		buffer.put('d');
		buffer.put('e');

	}
}
