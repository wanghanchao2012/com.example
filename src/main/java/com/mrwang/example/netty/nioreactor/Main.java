package com.mrwang.example.netty.nioreactor;

public class Main {

	public static void main(String[] args) {
		try {
			// 启动3个reactor，分别监听9999，10000，10001端口
			for (int i = 0; i < 3; i++) {
				Reactor reactor = new Reactor(9999 + i);
				new Thread(reactor).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
