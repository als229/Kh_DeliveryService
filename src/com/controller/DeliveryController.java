package com.controller;

import java.util.ArrayList;

import com.model.vo.Delivery;
import com.service.DeliveryService;
import com.view.DeliveryView;

public class DeliveryController {

	public void select(int num) {

		Delivery d = new DeliveryService().select(num);
		
		if(d==null) {
			new DeliveryView().displayNodata("조회결과 없습니다.");
		}else {
			new DeliveryView().display(d);
		}
		
	}

	public void insert(int dType, String sendAddr, String receiveAddr, String info) {
		
		int result = new DeliveryService().insert(dType,sendAddr,receiveAddr,info);
		
		if(result>0) {
			new DeliveryView().displaySuccess("택배가 성공적으로 보내졌습니당 ^^");
		}else {
			new DeliveryView().displayFail("택배보내기 실패....");
		}
		
	}

	public void delete(String productNum) {
		int result = new DeliveryService().delete(productNum);
		
		if(result > 0) {
			new DeliveryView().displaySuccess("배송시작하겠습니다~");
		}else {
			new DeliveryView().displayFail("그런 송장 번호 없는디요");
		}
		
	}

	public void searchAll() {
		ArrayList<String> list = new DeliveryService().searchAll();
		
		if(list.isEmpty()) {
			new DeliveryView().displayFail("조회에 실패했습니다.");
		}else {
			new DeliveryView().displayList(list);
		}
		
	}

}
