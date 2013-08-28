package com.benepia.card;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public abstract class CardServerAdapter implements CardSererverService{
	
	private Charset charset = null;
	private CharsetDecoder decoder = null;
	
	public CardServerAdapter(){
		charset = Charset.forName("EUC-KR");
		decoder = charset.newDecoder();
	}
	
	public String requestToServer(ByteBuffer massage) {
		
		String rsltMsg = "";
			
		try {
			massage.flip();
			String decMsg = decoder.decode(massage).toString();
			rsltMsg = requestToCardServer(decMsg);
		} catch (CharacterCodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rsltMsg;
	}
	
	public abstract String requestToCardServer(String msg);

}
