package com.benepia.pool.thread.processor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

import com.benepia.card.CardSererverService;
import com.benepia.card.bc.BcCardService;
import com.benepia.event.Job;
import com.benepia.event.NIOEvent;
import com.benepia.pool.PoolManager;
import com.benepia.pool.buffer.ByteBufferPoolIF;
import com.benepia.queue.BcCardServer;
import com.benepia.queue.Queue;
import com.benepia.util.CodeConstance;


public class ReadWriteProcessor extends Thread {
	
	private Queue queue = null;
	
	private static Charset 			charset = Charset.forName(CodeConstance.koEncTyp);
	private static CharsetDecoder 	decoder = charset.newDecoder();
	
	public ReadWriteProcessor(Queue queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Job job = queue.pop(NIOEvent.READ_EVENT);
				SelectionKey key = (SelectionKey) job.getSession().get("SelectionKey");
				SocketChannel sc = (SocketChannel) key.channel();
				try {
					response(sc);
				} catch (IOException e) {
					closeChannel(sc);
				} finally {
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	private void response(SocketChannel sc) throws IOException {
		
		ByteBufferPoolIF bufferPool = PoolManager.getByteBufferPool();
		ByteBuffer buffer = null;		
		try {
			
			if(!sc.isConnected() || !sc.isOpen()){
				throw new Exception();
			}
			
			buffer = bufferPool.getMemoryBuffer();
			
			sc.read(buffer);
			
			buffer.flip();
			String decMsg = decoder.decode(buffer).toString();
			
			//Business Logic
			CardSererverService cardSererverService = new BcCardService();
			String message = cardSererverService.requestToServer(decMsg);
			buffer.clear();
			buffer.put(message.getBytes());
			buffer.flip();

			sc.write(buffer);
			
		} catch(Exception e){
			
		} finally {
			closeChannel(sc);
		}		
	}
	
	private void closeChannel(SocketChannel sc) {
		try {
			sc.close();
			BcCardServer.getInstance().remove(sc);
		} catch (IOException e) {
		}
	}

}
