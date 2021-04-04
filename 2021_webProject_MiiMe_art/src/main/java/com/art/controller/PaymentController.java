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
	
	//결제완료시 art_sell_tb에 값 sold로 변경
	@RequestMapping("/updateStatus.do")
	public String updateStatus(PaymentVo p) {
		int re = dao.updateStatus(p);
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
	
	
}
