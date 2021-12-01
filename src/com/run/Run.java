package com.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.view.DeliveryView;

public class Run {

	public static void main(String[] args) {

//		Properties prop = new Properties();
//
//		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
//		prop.setProperty("url", "jdbc:oracle:thion:@localhost:1521:xe");
//		prop.setProperty("username", "JDBC");
//		prop.setProperty("password", "JDBC");
//		
//		try {
//			prop.store(new FileOutputStream("resources/driver.properties"), "driver.properties");
//			prop.storeToXML(new FileOutputStream("resources/Query.xml"), "Query.properties");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		new DeliveryView().mainMenu();
		
	}

}
