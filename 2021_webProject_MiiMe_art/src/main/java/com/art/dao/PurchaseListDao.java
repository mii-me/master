package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.DeliveryVo;
import com.art.vo.PurchaseListVo;

//by현익 / 구매목록 DAO / 210323
@Repository
public class PurchaseListDao {
	
	public List<PurchaseListVo> findAll(int memNo){
		//System.out.println("2. PurchasListDao-findAll 작동");
		//System.out.println("3. DBManager로 이동");
		return DBManager.listPurchase(memNo);
	}
}//class
