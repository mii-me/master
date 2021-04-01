package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ArtistCommentVo;
import com.art.vo.ArtistInfoVo;

@Repository
public class ArtistInfoDao {
	
	public int getTotalArtist(String keyword, String consonant, String sort) {
		System.out.println("2 .작가목록 개수 DAO 작동: keyword "+keyword+"/consonant "+consonant+"/sort "+sort);
		return DBManager.getTotalArtist(keyword, consonant, sort);
	}
	
	public int getTotalArtistArt(int memNo) {
//		System.out.println("2 .작품목록 개수 DAO 작동: memNo "+memNo);
		return DBManager.getTotalArtistArt(memNo);
	}
	
	public int getTotalArtistComment(int memNo) {
//		System.out.println("2 .작가기대평 개수 DAO 작동: memNo "+memNo);
		return DBManager.getTotalArtistComment(memNo);
	}
	
	
	public List<ArtistInfoVo> listArtistInfo(int start, int end, String keyword, String consonant, String sort){
		System.out.println("4. 작가목록 조회 DAO 작동: start "+start+"/end "+end+"/keyword "+keyword+"/consonant "+consonant+"/sort "+sort);
		return DBManager.listArtistInfo(start, end, keyword, consonant, sort);
	}

	public ArtistInfoVo selectArtistInfo(int memNo) {
		System.out.println("2. 작가프로필 조회 DAO 작동: memNo "+memNo);
		return DBManager.selectArtistInfo(memNo);
	}

	public List<ArtistInfoVo> topArtistInfo() {
		return DBManager.topArtistInfo();
	}
	
	public List<ArtistInfoVo> listArtistInfoArt(int memNo, int start, int end) {
//		System.out.println("4. 작품목록 조회 DAO 작동: memNo "+memNo+"/start "+start+"/end "+end);
		return DBManager.listArtistInfoArt(memNo, start, end);
	}

	public List<ArtistCommentVo> listArtistComment(int memNo, int start, int end) {
//		System.out.println("4. 작가기대평 조회 DAO 작동: memNo "+memNo+"/start "+start+"/end "+end);
		return DBManager.listArtistComment(memNo, start, end);
	}

	public int insertArtistComment(ArtistCommentVo ac) {
//		System.out.println("2. 작가기대평 등록 DAO 작동: ac "+ac);
		return DBManager.insertArtistComment(ac);
	}

	public int deleteArtistComment(int artistCmtNo) {
		System.out.println("2. 작가기대평 삭제 DAO 작동: artistCmtNo "+artistCmtNo);
		return DBManager.deleteArtistComment(artistCmtNo);
	}

	
}
