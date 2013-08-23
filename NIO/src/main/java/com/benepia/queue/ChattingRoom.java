package com.benepia.queue;

/*
 * Created on 2004. 7. 21.
 */

import java.util.Vector;

/**
 * @author Song Ji Hoon
 *
 */
public class ChattingRoom extends Vector {
	
	private static ChattingRoom instance = new ChattingRoom();
	
	public static ChattingRoom getInstance() {
		if (instance == null) {
			synchronized (ChattingRoom.class) {
				instance = new ChattingRoom();
			}
		}
		return instance;
	}
	
	private ChattingRoom() {}

}
