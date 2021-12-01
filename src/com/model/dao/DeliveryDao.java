package com.model.dao;

import static com.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import com.model.vo.Delivery;

public class DeliveryDao {

	private Properties prop = new Properties();

	public Delivery select(Connection conn, int num) {

		Delivery d = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

//		String sql = prop.getProperty("select");
		

		try {
			pstmt = conn.prepareStatement("SELECT * FROM DELIVERY WHERE D_NUM = ?");

			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				d = new Delivery(rset.getString("D_TYPE"), rset.getString("SEND_ADDR"), rset.getString("RECEIVE_ADDR"),
						rset.getString("INFO"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return d;
	}

	public int insert(String dType, String sendAddr, String receiveAddr, String info, Connection conn,String productNum) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "INSERT ALL "
				+ "INTO DELIVERY(D_NUM,D_TYPE,SEND_ADDR,RECEIVE_ADDR,INFO) VALUES(?,?,?,?,?)"
				+ "INTO RECORD(D_NUM,D_TYPE,SEND_ADDR,RECEIVE_ADDR) VALUES(?,?,?,?)"
				+ "SELECT* "
				+ "FROM DUAL";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productNum);
			pstmt.setString(2, dType);
			pstmt.setString(3, sendAddr);
			pstmt.setString(4, receiveAddr);
			pstmt.setString(5, info);
			pstmt.setString(6, productNum);
			pstmt.setString(7, dType);
			pstmt.setString(8, sendAddr);
			pstmt.setString(9, receiveAddr);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int delete(String productNum, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM DELIVERY WHERE D_NUM = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productNum);
			pstmt.addBatch();
			
			pstmt.executeBatch();
			pstmt.close();
			String sql2 = "UPDATE RECORD "
					+ "SET CONDITION = '출고완료'"
					+ "WHERE D_NUM = ?";
			
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setString(1, productNum);
			pstmt.addBatch();
			
			pstmt.executeBatch();
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<String> searchAll(Connection conn) {
		
		ArrayList<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		String sql = "SELECT D_NUM FROM DELIVERY";
		ResultSet rSet = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rSet = pstmt.executeQuery();
			
			while(rSet.next()) {
				String d = rSet.getString("D_Num");
				
				list.add(d);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rSet);
			close(pstmt);
		}
		
		
		
		return list;
	}

}