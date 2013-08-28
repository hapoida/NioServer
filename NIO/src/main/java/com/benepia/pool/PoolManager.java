package com.benepia.pool;


import java.util.HashMap;
import java.util.Map;

import com.benepia.pool.buffer.ByteBufferPoolIF;
import com.benepia.pool.selector.SelectorPoolIF;

public class PoolManager {
	
	private static Map map = new HashMap();
	
	private static PoolManager instance = new PoolManager();
	
	private PoolManager() {}
	
	public static void registAcceptSelectorPool(SelectorPoolIF selectorPool) {
		map.put("AcceptSelectorPool", selectorPool);
	}
	
	public static void registRequestSelectorPool(SelectorPoolIF selectorPool) {
		map.put("RequestSelectorPool", selectorPool);
	}
	
	public static SelectorPoolIF getAcceptSelectorPool() {
		return (SelectorPoolIF) map.get("AcceptSelectorPool");
	}
	
	public static SelectorPoolIF getRequestSelectorPool() {
		return (SelectorPoolIF) map.get("RequestSelectorPool");
	}
	
	public static void registByteBufferPool(ByteBufferPoolIF byteBufferPool) {
		map.put("ByteBufferPool", byteBufferPool);
	}
	
	public static ByteBufferPoolIF getByteBufferPool() {
		return (ByteBufferPoolIF) map.get("ByteBufferPool");
	}

}
