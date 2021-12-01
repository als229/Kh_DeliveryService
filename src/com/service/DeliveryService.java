package com.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;

import com.model.dao.DeliveryDao;
import com.model.vo.Delivery;

public class DeliveryService {

	public Delivery select(int num) {

		Connection conn = null;

		conn = getConnection();

		Delivery d = new DeliveryDao().select(conn, num);

		close(conn);

		return d;
	}

	public int insert(int dType, String sendAddr, String receiveAddr, String info) {
		Connection conn = getConnection();
		Delivery d = new Delivery();
		d.setdType(dType);
//		d.setSendAddr(sendAddr);
//		d.setInfo(info);
//		d.setReceiveAddr(receiveAddr);
		String productNum = productNumMake(dType, receiveAddr);

		int result = new DeliveryDao().insert(d.getdType(), sendAddr, receiveAddr, info, conn, productNum);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	public String productNumMake(int dType, String receiveAddr) {

		String localNum = "";
		String productType = "";
		String productNum = "";
		String rNum = "";

		if (receiveAddr.contains("서울")) {
			localNum = "002";
		} else if (receiveAddr.contains("경기")) {
			localNum = "031";
		} else if (receiveAddr.contains("제주")) {
			localNum = "064";
		} else if (receiveAddr.contains("부산")) {
			localNum = "051";
		} else {
			localNum = "000";
		}

		if (dType == 1) {
			productType = "01";
		} else if (dType == 2) {
			productType = "02";
		} else if (dType == 3) {
			productType = "03";
		} else if (dType == 4) {
			productType = "04";
		} else {
			productType = "99";
		}

		while (true) {
			ArrayList<String> list = searchAll();

			rNum = ((int) (Math.random() * 99999 + 10000)) + "";
			productNum = localNum + productType + rNum;
			if (!list.contains(productNum)) {
				break;
			}
		}

		return productNum;
	}

	public int delete(String productNum) {

		Connection conn = getConnection();
		int result = new DeliveryDao().delete(productNum, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public ArrayList<String> searchAll() {

		Connection conn = getConnection();
		ArrayList<String> list = new DeliveryDao().searchAll(conn);

		close(conn);

		return list;
	}

}
