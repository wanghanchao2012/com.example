package com.mrwang.example.netty.bilinbilin;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyClient {
	public static void main(String[] args) throws InterruptedException {

		EventLoopGroup loopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();

		try {
			bootstrap.group(loopGroup).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
							pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
							pipeline.addLast(new MyClientHander());
						}
					});
			ChannelFuture sync = bootstrap.connect("localhost", 8899).sync();
			sync.channel().closeFuture().sync();
		} finally {
			loopGroup.shutdownGracefully();
		}
	}
}
