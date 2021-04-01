package com.art.dao;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ArtistInfoVo;
import com.art.vo.MemberVo;

@Repository
public class JoinDao {
	
	public int insertMember(MemberVo m) {
		return DBManager.insertMember(m);
	}
	
	public int insertArtistInfo(ArtistInfoVo a) {
		return DBManager.insertArtistInfo(a);
	}

	public MemberVo selectMember(String memId) {
		System.out.println("2. 로그인 DAO 작동: memId "+memId);
		return DBManager.selectMember(memId);
	}
	
}
