package com.art.controller;

import java.util.List; 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.AuctionDao;
import com.art.vo.AuctionVo;
import com.google.gson.Gson;

@RestController
public class AuctionController {
	
	@Autowired
	private AuctionDao dao;
	public void setDao(AuctionDao dao) {
		this.dao = dao;
	}
	

	//핫딜 조건별 조회1, 찜 횟수(wishList_tb에서 count로 참조)
	@RequestMapping("/listHot_1.do")
	public String listHot_1() {
		System.out.println("AuctionController listHot_1 호출");
		String r="";
		List<AuctionVo> list = dao.listHot_1();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}

	
	//핫딜 조건별 조회2, 가장 입찰 횟수가 높은 작품
	@RequestMapping("/listHot_2.do")
	public String listHot_2() {
		System.out.println("AuctionController listHot_2 호출");
		String r="";
		List<AuctionVo> list = dao.listHot_2();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}
	
	//핫딜 조건별 조회3, 마감 임박
	@RequestMapping("/listHot_3.do")
	public String listHot_3() {
		System.out.println("AuctionController listHot_3 호출");
		String r="";
		List<AuctionVo> list = dao.listHot_3();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}
	
	//경매장에서 작품 불러오기   //03.18 by 현규
	@RequestMapping("/detailAuction.do")
	public List<AuctionVo> detailAuction(int artNo) {
		System.out.println("AuctionController detailAuction에서 artNo: '"+artNo+"'을 전송");
		List<AuctionVo> re=dao.detailAuction(artNo);
		System.out.println("Auction 컨트롤러 detailAuction에서 AuctionVo: "+re);
		return re;
	}
	

	//경매장에서 입찰가 업데이트 	//03.21 by 현규
	@RequestMapping("/updateBid.do")
	public String updateBid(AuctionVo a) {
		System.out.println("Auction 컨트롤러 updateBid에서 넘긴값: "+a);
		System.out.println("Auction 컨트롤러 updateBid 불러옴");
		int re = dao.updateBid(a);
		System.out.println("re: "+re);
		return re+"";
	}
	
	
	@RequestMapping("/remainFive.do")
	public String remainFive(AuctionVo a) {
		System.out.println("Auction 컨트롤러 updateBid에서 넘긴값: "+a);
		System.out.println("Auction 컨트롤러 updateBid 불러옴");
		int re = dao.remainFive(a);
		System.out.println("re: "+re);
		return re+"";
	}
	
	
	//입찰 성공할 때 입찰 횟수 증가
	@RequestMapping("/countBid.do")
	public String countBid(int artNo) {
		System.out.println("Auction 컨트롤러 countBid에서 넘긴 작품번호: "+artNo);
		int re = dao.countBid(artNo);
		System.out.println("re: "+re);
		return re+"";
	}

	//입찰가 초기화 (임시 생성)
	@RequestMapping("/resetBid.do")
	public String resetBid(AuctionVo a) {
		int re = dao.resetBid(a);
		return re+"";
	}
	
	//작품정보 불러오는 임시 메소드	//03.18 by 현규
	@RequestMapping("/listArts.do")
	public String listArts(){	
		System.out.println("컨트롤러에서 listArts 호출");
		String r="";
		List<AuctionVo> list = dao.listArts();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}
	
	//남은시간 출력
	@RequestMapping("/remainTime.do")
	public String remainTime(int artNo) {
		System.out.println("Auction 컨트롤러에서 remainTime 동작");
		System.out.println("artNo:"+artNo);
		AuctionVo av = dao.remainTime(artNo);
		Gson gson = new Gson();
		
		System.out.println("Auction 컨트롤러에서 remainTime AuctionVo 값: "+av);
		return gson.toJson(av);
	}
	
	//찜하기
	@RequestMapping("/insertWish.do")
	public String insertWish(int artNo,int memNo) {
		System.out.println("AuctionController insertWish에서 받은 작품번호: "+artNo+", 회원번호: "+memNo);
		int re = dao.insertWish(artNo,memNo);
		return re+"";
	}
	
	//찜 취소
	@RequestMapping("/deleteWish.do")
	public String deleteWish(int artNo,int memNo) {
		System.out.println("AuctionController deleteWish에서 받은 작품번호: "+artNo+", 회원번호: "+memNo);
		int re = dao.deleteWish(artNo,memNo);
		return re+"";
	}
	
	
	
	
}
