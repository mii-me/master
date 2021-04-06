package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.DeliveryVo;

//by현익 / 배송지관련 DAO / 210319
@Repository
public class DeliveryDao {

	public DeliveryVo findDefault(int memNo) {
		//System.out.println(" DeliveryDao-findDefault 작동");
		//System.out.println(" DBManager로 이동");
		return DBManager.defaultDelivery(memNo);
	}
	
	public List<DeliveryVo> findAll(int memNo){
		//System.out.println(" DeliveryDao-findAll 작동");
		//System.out.println(" DBManager로 이동");
		return DBManager.listDelivery(memNo);
	}
	
	public int insert(DeliveryVo dv) {
		//System.out.println("2. DeliveryDao-insertDelivery 작동");
		//System.out.println("3. DBManager로 이동");
		return DBManager.insertDelivery(dv);
	}//insert

	public int deleteDelivery(int no) {
		//System.out.println("2. DeliveryDao-deleteDelivery 작동");
		//System.out.println("3. DBManager로 이동");
		return DBManager.deleteDelivery(no);
	}
	
	public int changeDefaultDelivery(int memNo, int delNo) {
		//System.out.println("2. DeliveryDao-changeDefaultDelivery 작동");
		//System.out.println("3. DBManager로 이동");
		return DBManager.changeDefaultDelivery(memNo, delNo);
	}
	
}//class
