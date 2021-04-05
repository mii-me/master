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
	
	//판매상태 sold로 변경
	public int updateSold(PaymentVo p) {
		return DBManager.updateSold(p);
	}
	
	//판매상태 sale로 변경
	public int updateSale(PaymentVo p) {
		return DBManager.updateSale(p);
	}
	
	//최고입찰자 초기화
	public int resetTop(PaymentVo p) {
		return DBManager.resetTop(p);
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

	//남은시간 5초로 초기화 (테스트용)
	public int remainFive(AuctionVo a) {
		return DBManager.remainFive(a);
	}

	//남은시간 +5시간 추가(결제취소용)
	public int upTimeFive(AuctionVo a) {
		return DBManager.upTimeFive(a);
	}
	
}
