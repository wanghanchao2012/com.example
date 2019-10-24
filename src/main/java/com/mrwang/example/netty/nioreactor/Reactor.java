package com.mrwang.example.netty.nioreactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
	private final Selector selector;
	private final ServerSocketChannel serverSocketChannel;

	public Reactor(int port) throws IOException {
		// 创建选择器
		selector = Selector.open();
		// 开启通道
		serverSocketChannel = ServerSocketChannel.open();
		// 绑定端口
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		// 非阻塞模式
		serverSocketChannel.configureBlocking(false);
	}

	public static void main(String[] args) {
		try {
			Reactor reactor = new Reactor(9999);
			reactor.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			// 告诉服务员你现在叫“接待”，有客人来了就把他们带接进来
			SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					it.remove();
					key = it.next();
					dispatch(key);
				}
				keys.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void dispatch(SelectionKey k) throws IOException {
		if (!k.isValid()) {
			return;
		}
		if (k.isAcceptable()) {
			// 是外面来客人了，所以我现在是"接待"
			Acceptor acceptor = (Acceptor) k.attachment();
			acceptor.handler();
		}
		if (k.isReadable()) {
			// 客人要点菜了，所以叫一个点餐员来服务
			ReadEventHandler handler = (ReadEventHandler) k.attachment();
			new Thread(handler).start();
		}
	}

}