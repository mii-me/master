package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.art.db.DBManager;
import com.art.vo.ClientComplainVo;
import com.art.vo.DeliveryVo;

//by현익 / 고객센터 DAO / 210319
@Repository
public class ClientComplainDao {

	
	public List<ClientComplainVo> findAll(int memNo){
		System.out.println("2. ComplainDao-findAll 작동");
		System.out.println("3. DBManager로 이동");
		return DBManager.listComplain(memNo);
	}

	public int insert(ClientComplainVo cv) {
		System.out.println("2. ComplainDao-insertComplain 작동");
		System.out.println("3. DBManager로 이동");
		return DBManager.insertComplain(cv);
	}

	public int deleteComplain(int comNo) {
		System.out.println("4. ComplainDao-deleteComplain 작동");
		System.out.println("5. DBManager로 이동");
		return DBManager.deleteComplain(comNo);
	}
	
	public ClientComplainVo findOne(int comNo) {
		System.out.println("2. ComplainDao-findOne 작동");
		return DBManager.findOneComplain(comNo);
	}
	
}//class
