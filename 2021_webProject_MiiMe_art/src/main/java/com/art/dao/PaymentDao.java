package com.art.dao;


import java.util.List; 

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.AuctionVo;
import com.art.vo.MemberVo;
import com.art.vo.PaymentVo;

@Repository
public class PaymentDao {
	
	//payment_tb에 insert로 작품정보+결제 금액이 들어간다.
	public int insertPayment(PaymentVo p) {
		System.out.println("3.paymentDao에서 insertPayment의 PaymentVo: "+p);
		return DBManager.insertPayment(p);
	}
	
	//결제페이지에서 보여지는 작품정보
	public List<PaymentVo> findInfo(int artNo){
		System.out.println("paymentDao에서 findInfo 호출");
		return DBManager.findInfo(artNo);
	}

	//로그인한 회원정보를 가져온다.
	public PaymentVo getMember(int memNo) {
		System.out.println("3. paymentDao에서 getMember 호출");
		return DBManager.getMember(memNo);
	}
	
	//결제정보
	public int updateStatus(PaymentVo p) {
		return DBManager.updateStatus(p);
	}

	public int updatePoint(PaymentVo p) {
		return DBManager.updatePoint(p);
	}
	
	
}
