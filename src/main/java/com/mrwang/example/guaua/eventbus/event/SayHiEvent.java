package com.mrwang.example.guaua.eventbus.event;

public class SayHiEvent {
	private final String message;

	public SayHiEvent(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

}
