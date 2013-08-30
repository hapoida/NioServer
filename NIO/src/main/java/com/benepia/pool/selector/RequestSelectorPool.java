package com.benepia.pool.selector;


import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Iterator;

import com.benepia.pool.selector.handler.RequestHandler;
import com.benepia.queue.Queue;

public class RequestSelectorPool extends SelectorPoolAdaptor {

	private Queue queue = null;
	
	public RequestSelectorPool(Queue queue) {
		this(queue, 1);
	}
	
	public RequestSelectorPool(Queue queue, int size) {
		super.size = size;
		this.queue = queue;
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
		Thread handler = new RequestHandler(queue, selector, index);
		
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
