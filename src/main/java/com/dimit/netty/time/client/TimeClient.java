package com.dimit.netty.time.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import com.dimit.netty.time.server.TimeServer;

public class TimeClient {
	public static void main(String[] args) {
		new TimeClient().connect(TimeServer.PORT , TimeServer.IP);
	}

	private void connect(int port, String ip) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel arg0)
							throws Exception {
						arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
						arg0.pipeline().addLast(new StringDecoder());
						arg0.pipeline().addLast(new TimeClientHandler());
					}
				});
			ChannelFuture future = b.connect(ip, port).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
}
