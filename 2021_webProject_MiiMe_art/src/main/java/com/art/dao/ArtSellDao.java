package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ArtInfoVo;

@Repository
public class ArtSellDao {

	public int insertArtInfo(ArtInfoVo vo) {
		return DBManager.insertArtInfo(vo);
	}

	public List<ArtInfoVo> findSellCheckList(int memNo, int start, int end){
		return DBManager.findSellCheckList(memNo, start, end);
	}

	public int updateArtInfo(ArtInfoVo a) {
		return DBManager.updateArtInfo(a);
	}

	public int insertArtSell(int artNo, int memNo) {
		return DBManager.insertArtSell(artNo, memNo);
	}

	public int deleteArtSellCheck(int artNo) {
		return DBManager.deleteArtSellCheck(artNo);
	}

	public int deleteArtInfo(int artNo) {
		return DBManager.deleteArtInfo(artNo);
	}

	public List<ArtInfoVo> findArtSell(int memNo) {
		return DBManager.findArtSell(memNo);
	}

	public int cancleArtSell(int artNo) {
		return DBManager.cancleArtSell(artNo);
	}

	public int insertArtSellCheck(int artNo, int memNo) {
		return DBManager.insertArtSellCheck(artNo, memNo);
	}

	public int findArtistPoint(int memNo) {
		return DBManager.findArtistPoint(memNo);
	}

	public String findArtistName(int memNo) {
		return DBManager.findArtistName(memNo);
	}

	public int getTotalRecord(int memNo) {
		return DBManager.getTotalArtSellCheck(memNo);
	}


	public int updateArtistPoint(int resultPoint, int memNo) { 
		return DBManager.updateArtistPoint(resultPoint, memNo); 
	}
 

	
}
