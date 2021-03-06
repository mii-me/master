package com.art.controller;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.PaymentDao;
import com.art.vo.AuctionVo;
import com.art.vo.PaymentVo;

@RestController
public class PaymentController {

	@Autowired
	private PaymentDao dao;
	public void setDao(PaymentDao dao) {
		this.dao = dao;
	}
	
	//결제페이지에서 결제하기 눌렀을 때
	@RequestMapping("/insertPayment.do")
	public String insertPayment(PaymentVo p) {
		int re = dao.insertPayment(p);
		return re+"";	
	}
	
	//결제완료시 art_sell_tb에 값 sold로 변경
		@RequestMapping("/updatePoint.do")
		public String updatePoint(PaymentVo p) {
			int re = dao.updatePoint(p);
			return re+"";
		}
	
	//경매장 -> 결제페이지에 보여줄 작품정보
	@RequestMapping(value="/findInfo.do")
	public List<PaymentVo> findInfo(int artNo) {
		List<PaymentVo> re=dao.findInfo(artNo);
		return re;
	}
	
	//판매상태를 sale로 바꿔준다.
	@RequestMapping("/updateSold.do")
	public String updateSold(PaymentVo p) {
		int re = dao.updateSold(p);
		return re+"";
	}

	//판매상태를 sale로 바꿔준다.
	@RequestMapping("/updateSale.do")
	public String updateSale(PaymentVo p) {
		int re = dao.updateSale(p);
		return re+"";
	}
	
	//최고입찰자 초기화
	@RequestMapping("/resetTop.do")
	public String resetTop(PaymentVo p) {
		int re = dao.resetTop(p);
		return re+"";
	}
	
	//로그인한 회원정보 가져오기
	@RequestMapping(value="/getMember.do")
	public PaymentVo getMember(int memNo) {
	PaymentVo pv=dao.getMember(memNo);
	return pv;
	}
	
	
	//낙찰완료된 작품에 대한 결제대기 목록   //04.04 by 현규
	@RequestMapping("/payWait.do")
	public List<AuctionVo> payWait(int memNo) {
		List<AuctionVo> re=dao.payWait(memNo);
		return re;
	}
	
	//남은시간 +5시간 추가(결제취소용)
	@RequestMapping("/upTimeFive.do")
	public String upTimeFive(AuctionVo a) {
		int re = dao.upTimeFive(a);
		return re+"";
	}
	
	
}
