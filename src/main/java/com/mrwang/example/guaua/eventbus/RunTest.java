package com.mrwang.example.guaua.eventbus;

import com.google.common.eventbus.EventBus;
import com.mrwang.example.guaua.eventbus.event.SayHiEvent;
import com.mrwang.example.guaua.eventbus.event.TestEvent;
import com.mrwang.example.guaua.eventbus.listen.SayHiListener;

public class RunTest {
	
	public void testReceiveEvent() throws Exception {

		EventBus eventBus = new EventBus("test");
		SayHiListener sayHiListener = new SayHiListener();
		eventBus.register(sayHiListener);
		eventBus.post(new TestEvent(400));
		eventBus.post(new SayHiEvent("wanghanchao"));

	}

	public static void main(String[] args) throws Exception {
		new RunTest().testReceiveEvent();
	}
	
}
