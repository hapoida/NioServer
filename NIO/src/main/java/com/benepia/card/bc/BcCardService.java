package com.benepia.card.bc;

import com.benepia.card.CardServerAdapter;

public class BcCardService extends CardServerAdapter{
	
	@Override
	public String requestToCardServer(String msg) {
		// TO-DO BCī��翡 ��û�� ������ ���� �۾�
		return (msg + "�� BCī�忡 ��û ���� �� �Ϸ�");
	}
	
}
