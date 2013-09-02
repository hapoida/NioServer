package com.benepia.server;

import java.io.File;
import java.io.IOException;


import com.benepia.pool.PoolManager;
import com.benepia.pool.buffer.ByteBufferPool;
import com.benepia.pool.buffer.ByteBufferPoolIF;
import com.benepia.pool.selector.AcceptSelectorPool;
import com.benepia.pool.selector.RequestSelectorPool;
import com.benepia.pool.selector.SelectorPoolIF;
import com.benepia.pool.thread.ThreadPool;
import com.benepia.pool.thread.ThreadPoolIF;
import com.benepia.queue.BlockingEventQueue;
import com.benepia.queue.Queue;

public class AdvancedServer {
	
	private Queue queue = null;
	private SelectorPoolIF acceptSelectorPool = null;
	private SelectorPoolIF requestSelectorPool = null;
	
	private ByteBufferPoolIF byteBufferPool = null;
	
	ThreadPoolIF acceptThreadPool = null;
	ThreadPoolIF readWriteThreadPool = null;
	
	public AdvancedServer() {
		try {
			initResource();
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initResource() throws IOException {
		// ByteBufferPool 积己..
		File bufferFile = new File("C:/Buffer.tmp");
		if (!bufferFile.exists()) {
			bufferFile.createNewFile();
		}
		bufferFile.deleteOnExit();
		byteBufferPool = new ByteBufferPool(20*1024, 40*2048, bufferFile);
		
		// Queue 积己..   
		queue = BlockingEventQueue.getInstance();		
		
		// PoolManager 俊 ByteBufferPool 殿废..
		PoolManager.registByteBufferPool(byteBufferPool);
		
		// ThreadPool 积己..
		acceptThreadPool = new ThreadPool(
				queue, "com.benepia.pool.thread.processor.AcceptProcessor");
		readWriteThreadPool = new ThreadPool(
				queue, "com.benepia.pool.thread.processor.ReadWriteProcessor");

		// SelectorPool 积己..
		acceptSelectorPool = new AcceptSelectorPool(queue);
		requestSelectorPool = new RequestSelectorPool(queue);
		
		// PoolManager 俊 SelectorPool 殿废..
//		PoolManager.registAcceptSelectorPool(acceptSelectorPool);
		PoolManager.registRequestSelectorPool(requestSelectorPool);	
	}
	
	private void startServer() {
		acceptThreadPool.startAll();
		readWriteThreadPool.startAll();
		
		acceptSelectorPool.startAll();
		requestSelectorPool.startAll();
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		AdvancedServer server = new AdvancedServer();
	}
	
}
