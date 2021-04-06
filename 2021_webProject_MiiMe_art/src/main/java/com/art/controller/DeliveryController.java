package com.art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.DeliveryDao;
import com.art.vo.DeliveryVo;
import com.google.gson.Gson;

//by현익 / 배송지관련 컨트롤러 / 210319
@RestController
public class DeliveryController {

	
	  @Autowired private DeliveryDao dao; 
	  public void setDao(DeliveryDao dao) {
	  this.dao = dao; }


	@RequestMapping("/defaultDelivery.do")
	public DeliveryVo defaultDelivery(int memNo) {
		//System.out.println("_________________________________");
		//System.out.println("1. Controller-defaultDelivery 실행");
		DeliveryVo dv = dao.findDefault(memNo);
		return dv;
	}
	
	
	@RequestMapping("/listDelivery.do")
	public String listDelivery(int memNo) {
		//System.out.println("2. Controller-listDelivery 실행");
		String r = "";
		List<DeliveryVo> list = dao.findAll(memNo);
		Gson gson = new Gson();
		r = gson.toJson(list);
		return r;
	}

	@RequestMapping("/insertDelivery.do")
	public String insertDelivery(DeliveryVo dv) {
		//System.out.println("_________________________________");
		//System.out.println("1. Controller-insertDelivery 실행");
		String r = "ok";
		int re = dao.insert(dv);
		if (re !=1) {
			r="no";
		}//end if
		return r;
	}
	
	@RequestMapping("/deleteDelivery.do")
	public String deleteDelivery(int no) {
		//System.out.println("_________________________________");
		//System.out.println("1. Controller-deleteDelivery 실행");
		String r = "ok";
		int re = dao.deleteDelivery(no);
		if (re !=1) {
			r="no";
		}//end if
		return r;
	}
	@RequestMapping("/changeDefaultDelivery.do")
	public String changeDefaultDelivery(int memNo, int delNo) {
		//System.out.println("_________________________________");
		//System.out.println("1. Controller-changeDefaultDelivery 실행");
		String r = "ok";
		int re = dao.changeDefaultDelivery(memNo, delNo);
		if (re !=1) {
			r="no";
		}//end if
		return r;
	}
	
	
}//Controller
