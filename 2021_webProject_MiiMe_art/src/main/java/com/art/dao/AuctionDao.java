package com.art.dao;

import java.util.List; 

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.AuctionVo;

@Repository
public class AuctionDao {


	//경매장에서 보여지는 작품 정보
	public List<AuctionVo> detailAuction(int artNo){
		return DBManager.detailAuction(artNo);
	}
	
	//art_info_tb의 aucBid(입찰가)를 update	
	public int updateBid(AuctionVo a) {
		return DBManager.updateBid(a);
	}

	//입찰 성공할 때 입찰 횟수 증가		
	public int countBid(int artNo,int memNo) {
		return DBManager.countBid(artNo,memNo);
	}
	
	
	//핫딜 조건별 조회1, 찜 횟수(wishList_tb에서 count로 참조)	
	public List<AuctionVo> listHot_1() {
		return DBManager.listHot_1();
	}
	
	//조건1의 순위를 1씩 증가	
	public int updateRank1(AuctionVo a) {
		return DBManager.updateRank1(a);
	}
	
	//핫딜 조건별 조회2, 가장 입찰 횟수가 높은 작품
	public List<AuctionVo> listHot_2() {
		return DBManager.listHot_2();
	}
	
	//조건2의 순위를 1씩 증가	
	public int updateRank2(AuctionVo a) {
		return DBManager.updateRank2(a);
	}
	
	//핫딜 조건별 조회3, 마감임박 작품
	public List<AuctionVo> listHot_3() {
		return DBManager.listHot_3();
	}
	
	//조건3의 순위를 1씩 증가	
	public int updateRank3(AuctionVo a) {
		return DBManager.updateRank3(a);
	}
	
	
	//경매장에서 작품에 대한 마감시간 표시
	public AuctionVo remainTime(int artNo) {
		return DBManager.remainTime(artNo);
	}

	//남은시간 5초로 초기화 (테스트용)
	public int remainFive(AuctionVo a) {
		return DBManager.remainFive(a);
	}
	
	
}
