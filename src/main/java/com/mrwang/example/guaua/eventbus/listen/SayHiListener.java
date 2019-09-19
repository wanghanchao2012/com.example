package com.mrwang.example.guaua.eventbus.listen;

import com.google.common.eventbus.Subscribe;
import com.mrwang.example.guaua.eventbus.event.SayHiEvent;
import com.mrwang.example.guaua.eventbus.event.TestEvent;

public class SayHiListener {

	public String message;

	@Subscribe
	public void listen(SayHiEvent event) {
		String message = event.getMessage();
		System.out.println("SayHiEvent say hi : " + message);
		this.message = message;
	}

	@Subscribe
	public void listen(TestEvent event) {
		System.out.println("TestEvent say hi listener Message:" + event.getMessage());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
