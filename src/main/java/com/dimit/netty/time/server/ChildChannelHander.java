package com.dimit.netty.time.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


public class ChildChannelHander extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
		arg0.pipeline().addLast(new StringDecoder());
		arg0.pipeline().addLast(new TimeServerHandler());
	}


}
