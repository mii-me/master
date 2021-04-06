package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ArtInfoVo;


@Repository
public class ArtInfoDAO {
	
	
	public List<ArtInfoVo> listArt(int start, int end, String responseTheme, int sizeRange1,int sizeRange2,int responsePrice) {
		return DBManager.listArt(start,end,responseTheme,sizeRange1,sizeRange2,responsePrice);
	}
	public int totArt(String responseTheme,int sizeRange1,int sizeRange2, int responsePrice) {
		return DBManager.totArt(responseTheme,sizeRange1,sizeRange2,responsePrice);
	}

	public ArtInfoVo detailArt(int memNo, int artNo) {
		return DBManager.detailArt(memNo,artNo); 
	}
	
	public List<ArtInfoVo> detailArtHash(String tag) {
		return DBManager.detailArtHash(tag); 
	}

	public int insertWishList(int userNo, int artNo) {
		return DBManager.insertWishList(userNo,artNo);
	}
	public int deleteWishList(int userNo, int artNo) {
		return DBManager.deleteWishList(userNo,artNo);
	}
	
	
	
}
