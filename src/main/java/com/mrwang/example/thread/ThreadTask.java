package com.mrwang.example.thread;

public class ThreadTask implements Runnable {

	public ThreadTask() {

	}

	public void run() {
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
	}

}
