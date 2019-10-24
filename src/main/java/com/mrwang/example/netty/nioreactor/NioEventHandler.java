package com.mrwang.example.netty.nioreactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class NioEventHandler implements Runnable {
	protected final Selector selector;
	protected final SelectionKey selectionKey;

	public NioEventHandler(Selector selector, SelectionKey key) {
		this.selector = selector;
		this.selectionKey = key;
	}

	protected abstract void handler() throws IOException;

	@Override
	public void run() {
		try {
			handler();
		} catch (IOException e) {
			log.error("handler field with io Exception ", e);
		}
	}
}