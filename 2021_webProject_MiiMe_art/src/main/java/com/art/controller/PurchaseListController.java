package com.art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.PurchaseListDao;
import com.art.vo.PurchaseListVo;
import com.google.gson.Gson;

//by현익 / 구매목록 컨트롤러 / 210323
@RestController
public class PurchaseListController {

	
	  @Autowired private PurchaseListDao dao; 
	  public void setDao(PurchaseListDao dao) {
	  this.dao = dao; }

	
	@RequestMapping("/purchaseList.do")
	public String purchaseList(int memNo) {
		//System.out.println("1. Controller-purchaseList 실행");
		String r = "";
		List<PurchaseListVo> list = dao.findAll(memNo);
		Gson gson = new Gson();
		r = gson.toJson(list);
		return r;
	}
	
	
}//Controller
