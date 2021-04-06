package com.art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.WishListDao;
import com.art.vo.ArtInfoVo;
import com.art.vo.WishListVo;
import com.google.gson.Gson;

//by현익 / 찜목록 컨트롤러 / 210323
@RestController
public class WishListController {

	
	  @Autowired private WishListDao dao; 
	  public void setDao(WishListDao dao) {
	  this.dao = dao; }

	
	@RequestMapping("/wishList.do")
	public String wishList(int memNo) {
		//System.out.println("_______________________________");
		//System.out.println("1. Controller-wishList 실행");
		String r = "";
		List<WishListVo> list = dao.findAll(memNo);
		Gson gson = new Gson();
		r = gson.toJson(list);
		return r;
	}
	//by 현익 / artPic으로 url에 필요한 artistNo, artNo, tag1을 구한다/ 210402 
	@RequestMapping("/findArtElements.do")
	public ArtInfoVo findArtElements(String artPic) {
		//System.out.println("_______________________________");
		//System.out.println("1. Controller-findArtElements 실행");
		String r = "";
		ArtInfoVo av = dao.findOne(artPic);
		return av;
	}
	
	
	
}//Controller
