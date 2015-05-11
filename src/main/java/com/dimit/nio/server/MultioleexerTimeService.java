package com.dimit.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class MultioleexerTimeService implements Runnable{
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private boolean stop;

	public MultioleexerTimeService(int port) {
		try {
			//构建多路复用选择器
			selector = Selector.open();
			//开启连接管道
			serverChannel = ServerSocketChannel.open();
			//设置为飞阻塞方式
			serverChannel.configureBlocking(false);
			//绑定端口
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);
			//将选择器注入连接通道中
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
		}
	}
	
}
