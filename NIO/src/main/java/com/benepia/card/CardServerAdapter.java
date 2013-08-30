package com.benepia.card;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public abstract class CardServerAdapter implements CardSererverService{
	
	public String requestToServer(String massage) {
		
		String rsltMsg = "";
		
		rsltMsg = requestToCardServer(massage);
		
		return rsltMsg;
	}
	
	public abstract String requestToCardServer(String msg);

}
