package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ChangeInfoVo;
import com.art.vo.DeliveryVo;

//by현익 / 배송지관련 DAO / 210319
@Repository
public class ChangeMemberInfoDao {
	public List<ChangeInfoVo> findAll(int memNo){
		System.out.println("2. ChangeMemberInfoDao-findAll 작동");
		System.out.println("3. DBManager로 이동");
		return DBManager.detailMemberInfo(memNo);
	}

	public int updateMemberInfo(ChangeInfoVo cv) {
		System.out.println("2. ChangeMemberInfoDao-updateMemberInfo 작동");
		System.out.println("3. DBManager로 이동");
		return DBManager.updateMemberInfo(cv);
	}

	public int updateArtistInfo(ChangeInfoVo cv) {
		System.out.println("2. ChangeMemberInfoDao-updateArtistInfo 작동");
		System.out.println("3. DBManager로 이동");
		return DBManager.updateArtistInfo(cv);
	}
	
}//class
