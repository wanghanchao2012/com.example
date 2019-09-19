package com.mrwang.example.guaua.eventbus.listen;

import com.google.common.eventbus.Subscribe;
import com.mrwang.example.guaua.eventbus.event.TestEvent;

public class EventListener {
	public int lastMessage = 0;

	@Subscribe
	public void listen(TestEvent event) {
		lastMessage = event.getMessage();
		System.out.println("Message:" + lastMessage);
	}

	public int getLastMessage() {
		return lastMessage;
	}
}