package com.art.dao;

import java.util.List; 

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.AuctionVo;

@Repository
public class AuctionDao {


	//경매장에서 보여지는 작품 정보
	public List<AuctionVo> detailAuction(int artNo){
		System.out.println("dao에서 detailAuction 호출");
		return DBManager.detailAuction(artNo);
	}
	
	//art_info_tb의 aucBid(입찰가)를 update	
	public int updateBid(AuctionVo a) {
		return DBManager.updateBid(a);
	}

	//입찰 성공할 때 입찰 횟수 증가		
	public int countBid(int artNo) {
		return DBManager.countBid(artNo);
	}
	
	//입찰가 초기화 (테스트용)		
	public int resetBid(AuctionVo a) {
		return DBManager.resetBid(a);
	}
	
	//핫딜 조건별 조회1, 찜 횟수(wishList_tb에서 count로 참조)	
	public List<AuctionVo> listHot_1() {
		System.out.println("dao에서 listHot_1 호출");
		return DBManager.listHot_1();
	}
	
	//핫딜 조건별 조회2, 가장 입찰 횟수가 높은 작품
	public List<AuctionVo> listHot_2() {
		System.out.println("dao에서 listHot_2 호출");
		return DBManager.listHot_2();
	}
	
	//핫딜 조건별 조회3, 마감임박 작품
	public List<AuctionVo> listHot_3() {
		// TODO Auto-generated method stub
		System.out.println("dao에서 listHot_3 호출");
		return DBManager.listHot_3();
	}
	
	//작품보기 임시 구현 (artList.html)	
	public List<AuctionVo> listArts() {
		System.out.println("dao에서 listArts 호출");
		return DBManager.listArts();
	}
	
	//경매장에서 작품에 대한 마감시간 표시
	public AuctionVo remainTime(int artNo) {
		System.out.println("AuctionDao에서 remainTime 동작");
		return DBManager.remainTime(artNo);
	}

	//찜하기
	public int insertWish(int artNo,int memNo) {
		System.out.println("AuctionDao에서 insertWish 동작");
		return DBManager.insertWish(artNo,memNo);
	}
	
	//찜취소
	public int deleteWish(int artNo,int memNo) {
		System.out.println("AuctionDao에서 deleteWish 동작");
		return DBManager.deleteWish(artNo,memNo);
	}

	//남은시간 5초로 초기화 (테스트용)
	public int remainFive(AuctionVo a) {
		return DBManager.remainFive(a);
	}


	
}
