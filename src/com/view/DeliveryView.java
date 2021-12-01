package com.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.controller.DeliveryController;
import com.model.vo.Delivery;

public class DeliveryView {

	Scanner sc = new Scanner(System.in);
	DeliveryController dc = new DeliveryController();

	public void mainMenu() {

		while (true) {
			System.out.println("안녕하세요~ 이용하실 서비스를 입력해주세용~");
			System.out.println("1.택배 입고");
			System.out.println("2.운송장 번호 조회");
			System.out.println("3.택배 출고");
			System.out.println("4.송장번호 조회하기");
			System.out.println("5.프로그램 종료");
			System.out.print("입력 번호 : ");

			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				insert(); // dType, sendAddr, infor, receiveAddr 받아야함
				break;
			case 2:
				select(); // 경휘
				break;
			case 3:
				delete(); // 송장번호로 출고시키기
				break;
			case 4:
				searchAll();
				break;
			case 5: 
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못입력하셨습니다");

			}

		}
	}

	private void searchAll() {
		System.out.println("송장번호 전체 조회하기 입니다.");
		
		new DeliveryController().searchAll();
		
	}

	private void delete() {
		System.out.println("가져가실 택배 송장번호를 입력해 주세용");
		System.out.print("송장번호 : ");
		String productNum = sc.nextLine();

		new DeliveryController().delete(productNum);
	}

	private void insert() {
		System.out.println("보내실 택배 정보를 넣어주세용");

		System.out.println("1. 도서");
		System.out.println("2. 유리");
		System.out.println("3. 가전");
		System.out.println("4. 의류");
		System.out.print("상품 종류 : ");
		int dType = sc.nextInt();
		sc.nextLine();
		System.out.print("보내시는 분 주소 : ");
		String sendAddr = sc.nextLine();
		System.out.print("받으시는 분 주소 : ");
		String receiveAddr = sc.nextLine();
		System.out.print("요청사항을 죽어주세용 : ");
		String info = sc.nextLine();

		new DeliveryController().insert(dType, sendAddr, receiveAddr, info);
	}

	public void select() {
		System.out.println("운송장 번호 조회");
		System.out.println("송장번호를 입력해 주세용");
		System.out.print("송장 번호 : ");
		int num = sc.nextInt();
		sc.nextLine();

		dc.select(num);

	}

	public void displaySuccess(String message) {
		System.out.println("요청 성공!" + message);
	}

	public void displayFail(String message) {
		System.out.println("요청 실패..." + message);
	}

	public void displayNodata(String message) {

		System.out.println(message);

	}
	
	public void displayList(ArrayList<String> list) {
		
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

	public void display(Delivery d) {

		System.out.println(d);
	}

}