package com.benepia.card.bc;

import com.benepia.card.CardServerAdapter;

public class BcCardService extends CardServerAdapter{
	
	public BcCardService(){
		super();
	}
	
	@Override
	public String requestToCardServer(String msg) {
		// BCī��翡 ��û�� ������ ���� �۾�
		return (msg + "�� BCī�忡 ��û ���� �� �Ϸ�");
	}
	
}
