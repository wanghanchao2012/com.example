package com.mrwang.example.guaua.eventbus.event;

public class TestEvent {
	private final int message;

	public TestEvent(int message) {
		this.message = message;
		System.out.println("event message:" + message);
	}

	public int getMessage() {
		return message;
	}
}