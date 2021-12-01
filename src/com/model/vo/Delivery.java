package com.model.vo;

public class Delivery {

	//운송장 번호는 지역에 맞는 번호3자리와 물건종류에 맞는 2자리 랜덤으로 생성되는 5글자
	//운송장 번호를 가지고 뭔가 이제품의 속성을 알고싶으면 섭스트링으로 앞에서 3글자 떼면 지역
	//3번째 부터 2글자 때면 물건종류가 나오고 
	//결과적으로 데이터 가공처리니깐 뷰단에서 그냥 다 받고 컨트롤러에서 연산시켜서 필드부에 저장시키면서
	//생성자부에 꼽아주는 식으로 데이터 처리 >
	private String dType; //물품종류
	private String sendAddr; //보내는사람 주소
	private String info; //고객 요청사항 필드
	private String receiveAddr; // 받는사람 주소
	private String productNum; // 송장번호 = > 앞에 두 글자 지역번호 3글자, 그 다음 2글자 물건종류, 렌덤숫자 5자
							   // 송장번호 만드는 메서드 만들어야함.
	
	public Delivery() {}

	public Delivery(String productNum) {
		this.productNum = productNum;
	}
	
	// inset에서 입력받은 값 저장할 때 쓸 생성자
	public Delivery(String dType, String sendAddr, String info, String receiveAddr) {
		this.dType = dType;
		this.sendAddr = sendAddr;
		this.info = info;
		this.receiveAddr = receiveAddr;
	}
	// 왜 있는 넘이지?
//	public Delivery(String dType, String sendAddr, String receiveAddr) {
//		this.dType = dType;
//		this.sendAddr = sendAddr;
//		this.receiveAddr = receiveAddr;
//	}
	public Delivery(String dType, String sendAddr, String infor ,String receiveAddr,String productNum ) {
		this.dType = dType;
		this.sendAddr = sendAddr;
		this.info = infor;
		this.receiveAddr = receiveAddr;
		this.productNum = productNum;
	}
	
	

	public String getdType() {
		return dType;
	}

	public void setdType(int dType) {
		
		if(dType == 1) {
			this.dType = "도서";
		}else if(dType == 2) {
			this.dType = "유리";
		}else if(dType == 3) {
			this.dType = "가전";
		}else if(dType == 4) {
			this.dType = "의류";
		}
	}

	
	public String getProductNum() {
		return productNum;
	}

	public void setProduct(String productNum) {
		this.productNum = productNum;
	}
	
	public String getSendAddr() {
		return sendAddr;
	}

	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	@Override
	public String toString() {
		return "Delivery [dType=" + dType + ", sendAddr=" + sendAddr + ", info=" + info + ", receiveAddr=" + receiveAddr
				+ "]";
	}
	
	/*
	 * 1. 택배 부치기 -insert  <<고객이 요청을 하고 그것을 가져와서 디비에 꼽는거 
	 * 2. 택배 조회하기 - select(운송장번호로) << 고객이 요청한 정보를 토대로 디비에서 찾아보는거 //경휘
	 * 3. 택배원 자기한테 맞는 지역택배 수거하기 (delete)
	 * 
	 * 택배를 관리하는 허브 즉 터미널에서 사용한다는 가정하에 
	 * 택배를 분류하고 출하해야하는 곳에서 들어오는 물건들에 대해 인서트로 가지고 있는 물건으로 
	 * 테이블에 가지고 있다가 택배원이 자기지역으로 물건을 배송할때 딜리트로 가지고 나간다.
	 * 또 테이블은 2개로 기록을 남길 수 있는 전체 기록 테이블이 있고, 현재 가지고있는 물건 
	 */
	
}
