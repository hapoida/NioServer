package com.benepia.card;

public abstract class CardServerAdapter implements CardSererverService{
	
	public String requestToServer(String massage) {
		
		String rsltMsg = "";
		
		rsltMsg = requestToCardServer(massage);
		
		return rsltMsg;
	}
	
	public abstract String requestToCardServer(String msg);

}
