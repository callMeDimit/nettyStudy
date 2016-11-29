package com.dimit.netty.time.client;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.dimit.netty.time.server.TimeServerHandler;

public class TimeClientHandler extends ChannelHandlerAdapter {
	private byte [] req ;
	private int count;
	public TimeClientHandler() {
		req = (TimeServerHandler.QUERY_CMD + System
				.getProperty("line.separator")).getBytes();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf msg = null;
		for(int i = 0 ; i<10 ;i++) {
			msg = Unpooled.buffer(req.length);
			msg.writeBytes(req);
			ctx.writeAndFlush(msg);
//			TimeUnit.SECONDS.sleep(1);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String body =(String) msg ;
		System.out.println("now is :" + body + " count :" + ++ count);
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}
	
	
	
	
	
}
