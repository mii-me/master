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
		String r="";
		List<AuctionVo> list = dao.listHot_1();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}

	
	//핫딜 조건별 조회2, 가장 입찰 횟수가 높은 작품
	@RequestMapping("/listHot_2.do")
	public String listHot_2() {
		String r="";
		List<AuctionVo> list = dao.listHot_2();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}
	
	//핫딜 조건별 조회3, 마감 임박
	@RequestMapping("/listHot_3.do")
	public String listHot_3() {
		String r="";
		List<AuctionVo> list = dao.listHot_3();
		Gson gson = new Gson();
		r= gson.toJson(list);
		return r;
	}
	
	//경매장에서 작품 불러오기   //03.18 by 현규
	@RequestMapping("/detailAuction.do")
	public List<AuctionVo> detailAuction(int artNo) {
		List<AuctionVo> re=dao.detailAuction(artNo);
		return re;
	}
	

	//경매장에서 입찰가 업데이트 	//03.21 by 현규
	@RequestMapping("/updateBid.do")
	public String updateBid(AuctionVo a) {
		int re = dao.updateBid(a);
		return re+"";
	}
	
	
	//작품 마감시간 5초로 업데이트(테스트용)
	@RequestMapping("/remainFive.do")
	public String remainFive(AuctionVo a) {
		int re = dao.remainFive(a);
		return re+"";
	}
	
	
	//입찰 성공할 때 입찰 횟수 증가
	@RequestMapping("/countBid.do")
	public String countBid(int artNo,int memNo) {
		int re = dao.countBid(artNo,memNo);
		return re+"";
	}

	//남은시간 출력
	@RequestMapping("/remainTime.do")
	public String remainTime(int artNo) {
		AuctionVo av = dao.remainTime(artNo);
		Gson gson = new Gson();
		return gson.toJson(av);
	}
	
	
}
