package com.art.controller;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.PaymentDao;
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
		System.out.println("1.Payent컨트롤러에서 PaymentVo: "+p);
		System.out.println("2.Payment컨트롤러에서 re(1이면 성공): "+re);
		return re+"";	
	}
	
	//결제완료시 art_sell_tb에 값 sold로 변경
		@RequestMapping("/updatePoint.do")
		public String updatePoint(PaymentVo p) {
			System.out.println("PaymentController에 updatePoint 동작");
			System.out.println("updatePoint 요청 값: "+p);
			int re = dao.updatePoint(p);
			System.out.println("re: "+re);
			return re+"";
		}
	
	//경매장 -> 결제페이지에 보여줄 작품정보
	@RequestMapping(value="/findInfo.do")
	public List<PaymentVo> findInfo(int artNo) {
		List<PaymentVo> re=dao.findInfo(artNo);
		return re;
	}
	
	//로그인한 회원정보 가져오기
	@RequestMapping(value="/getMember.do")
	public PaymentVo getMember(int memNo) {
		System.out.println("1.pay컨트롤러에서 getMember 동작");
		System.out.println("2. memNo: "+memNo);
		PaymentVo pv=dao.getMember(memNo);
		return pv;
	}
	
	//결제완료시 art_sell_tb에 값 sold로 변경
	@RequestMapping("/updateStatus.do")
	public String updateStatus(PaymentVo p) {
		System.out.println("PaymentController에 updateStatus 동작");
		System.out.println("updateStatus 요청 값: "+p);
		int re = dao.updateStatus(p);
		System.out.println("re: "+re);
		return re+"";
	}
	
}
