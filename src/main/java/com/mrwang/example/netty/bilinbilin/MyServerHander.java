package com.mrwang.example.netty.bilinbilin;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHander extends SimpleChannelInboundHandler<String> {
 

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("server accept message:" + msg);
		ctx.channel().writeAndFlush("server 的信息 ：" + UUID.randomUUID().toString());
	}

}
