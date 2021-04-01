package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.DeliveryVo;
import com.art.vo.PurchaseListVo;
import com.art.vo.WishListVo;

//by현익 / 찜목록 DAO / 210323
@Repository
public class WishListDao {
	
	public List<WishListVo> findAll(int memNo){
		System.out.println("2. WishListDao-findAll 작동");
		System.out.println("3. DBManager로 이동");
		return DBManager.listWish(memNo);
	}

}//class
