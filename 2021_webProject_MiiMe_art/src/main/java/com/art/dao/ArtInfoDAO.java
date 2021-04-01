package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ArtInfoVo;


@Repository
public class ArtInfoDAO {
	
	
	public List<ArtInfoVo> listArt(int start, int end, String responseTheme, int sizeRange1,int sizeRange2,int responsePrice) {
		System.out.println("ArtDAO의 listArt작동함");
		return DBManager.listArt(start,end,responseTheme,sizeRange1,sizeRange2,responsePrice);
	}
	public int totArt(String responseTheme,int sizeRange1,int sizeRange2, int responsePrice) {
		System.out.println("ArtDAO의 totArt 작동함.");
		//System.out.println("ArtDAO의 sizeRang1 : " + sizeRange1 + ",sizeRange2 : " + sizeRange2);
		return DBManager.totArt(responseTheme,sizeRange1,sizeRange2,responsePrice);
	}

	public ArtInfoVo detailArt(int memNo, int artNo) {
		System.out.println("ArtDAO의 detailArt작동함"); 
		return DBManager.detailArt(memNo,artNo); 
	}
	
	public List<ArtInfoVo> detailArtHash(String tag) {
		System.out.println("ArtDAO의 detailArtHash작동함"); 
		return DBManager.detailArtHash(tag); 
	}

	public int insertWishList(int userNo, int artNo) {
		System.out.println("ArtDAO의 insertWishList작동함"); 
		return DBManager.insertWishList(userNo,artNo);
	}
	public int deleteWishList(int userNo, int artNo) {
		System.out.println("ArtDAO의 deleteWishList작동함"); 
		return DBManager.deleteWishList(userNo,artNo);
	}
	
	
	
}
