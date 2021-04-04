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
		return DBManager.insertPayment(p);
	}
	
	//결제페이지에서 보여지는 작품정보
	public List<PaymentVo> findInfo(int artNo){
		return DBManager.findInfo(artNo);
	}
	
	//결제정보
	public int updateStatus(PaymentVo p) {
		return DBManager.updateStatus(p);
	}

	//결제 완료 후 포인트 업데이트
	public int updatePoint(PaymentVo p) {
		return DBManager.updatePoint(p);
	}
	
	//낙찰완료된 작품에 대한 결제대기 목록
	public List<AuctionVo> payWait(int memNo) {
		return DBManager.payWait(memNo);
	}
	
	//로그인한 회원정보를 가져온다.
	public PaymentVo getMember(int memNo) {
	return DBManager.getMember(memNo);
	}
	
}
