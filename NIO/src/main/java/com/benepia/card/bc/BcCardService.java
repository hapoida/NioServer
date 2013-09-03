package com.benepia.card.bc;

import com.benepia.card.CardServerAdapter;

public class BcCardService extends CardServerAdapter{
	
	@Override
	public String requestToCardServer(String msg) {
		// TO-DO BC카드사에 요청을 진행할 내용 작업
		return (msg + "를 BC카드에 요청 진행 후 완료");
	}
	
}
