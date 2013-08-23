package com.benepia.pool.selector.handler;


import java.nio.channels.SocketChannel;

public abstract class HandlerAdaptor extends Thread {
	
	// template method..
	public abstract void run();
	public abstract void addClient(SocketChannel sc);

}
