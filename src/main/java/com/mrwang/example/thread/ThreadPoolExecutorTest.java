package com.mrwang.example.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

public class ThreadPoolExecutorTest {

	private static ExecutorService pool;

	@Autowired
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
		pool = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < 3; i++) {
			ThreadTask threadTask = new ThreadTask();
			pool.execute(threadTask);
		}
	}

}
