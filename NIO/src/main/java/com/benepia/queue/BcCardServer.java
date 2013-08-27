package com.benepia.queue;

/*
 * Created on 2004. 7. 21.
 */

import java.util.Vector;

public class BcCardServer extends Vector {
	
	private static BcCardServer instance = new BcCardServer();
	
	public static BcCardServer getInstance() {
		if (instance == null) {
			synchronized (BcCardServer.class) {
				instance = new BcCardServer();
			}
		}
		return instance;
	}
	
	private BcCardServer() {}

}
