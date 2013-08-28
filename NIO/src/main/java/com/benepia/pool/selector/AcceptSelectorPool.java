package com.benepia.pool.selector;


import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Iterator;

import com.benepia.pool.selector.handler.AcceptHandler;
import com.benepia.queue.Queue;
import com.benepia.util.CodeConstance;

public class AcceptSelectorPool extends SelectorPoolAdaptor {
	
	private int port = CodeConstance.serverPort;
	private Queue queue = null;
	
	public AcceptSelectorPool(Queue queue) {
		this(queue, 1, CodeConstance.serverPort);
	}
	
	public AcceptSelectorPool(Queue queue, int size, int port) {
		super.size = size;		
		this.queue = queue;
		this.port = port;
		init();
	}
	
	private void init() {
		for (int i = 0; i < size; i++) {
			pool.add(createHandler(i));
		}
	}
	
	protected Thread createHandler(int index) {
		Selector selector = null;
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread handler = new AcceptHandler(queue, selector, port, index);
		
		return handler;
	}

	public void startAll() {
		Iterator iter = pool.iterator();
		while (iter.hasNext()) {
			Thread handler = (Thread) iter.next();
			handler.start();
		}
	}

	public void stopAll() {
		Iterator iter = pool.iterator();
		while (iter.hasNext()) {
			Thread handler = (Thread) iter.next();
			handler.interrupt();
			handler = null;
		}
	}

}
