package com.benepia.queue;

import com.benepia.event.Job;

public interface Queue {
	
	public Job pop(int eventType);
	public void push(Job job);

}
