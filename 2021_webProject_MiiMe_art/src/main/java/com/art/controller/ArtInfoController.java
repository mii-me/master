package com.art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.ArtInfoDAO;
import com.art.vo.ArtInfoVo;
import com.art.vo.ArtResponse;
import com.google.gson.Gson;

//view를 응답하는 어노테이션 : @Controller
//Data만 응답하는 ,Controller + ResponseBody인 어노테이션 : RestController
@RestController
public class ArtInfoController {
	
	public static int pageIN = 9; //한 페이지에 들어갈 게시물 갯수
	public static int totalRecord = 0; //총 게시물 갯수
	public static int totalPage = 1; //총 페이지 수
	

	@Autowired
	private ArtInfoDAO dao;	
	
	public void setDao(ArtInfoDAO dao) {
		this.dao = dao;
	}
	
	

	@RequestMapping("/insertWishList.do")
	public String insertWishList(int userNo, int artNo) {
		String r = "찜 목록에 추가되었습니다.";
		int re = dao.insertWishList(userNo,artNo);
		if(re != 1) {
			r = "찜 목록 추가에 실패하였습니다.";
		}
		//System.out.println("Controller의 insertWishList 동작함");
		
		return r;
	}
	@RequestMapping("/deleteWishList.do")
	public String deleteWishList(int userNo, int artNo) {
		String r = "찜 목록에서 삭제되었습니다.";
		int re = dao.deleteWishList(userNo,artNo);
		if(re != 1) {
			r = "찜 목록에서 삭제 실패하였습니다.";
		}
		//System.out.println("Controller의 deleteWishList 동작함");
		
		return r;
	}
	
	 
	//sizeRange1,2는 VO의 art_size1,2와 같음.
	@RequestMapping("/listArt.do")
	public String listArt(int pageNO,String responseTheme,int sizeRange1,int sizeRange2,int responsePrice) {
		
		totalRecord = dao.totArt(responseTheme,sizeRange1,sizeRange2,responsePrice);
		totalPage = (int)Math.ceil(totalRecord/(double)pageIN);
		
		int start = (pageNO-1)*pageIN+1; //현재 페이지에서 시작하는 게시물 번호
		int end = start+pageIN-1; //현재 페이지의 마지막 게시물 번호
		List<ArtInfoVo> list = dao.listArt(start,end,responseTheme,sizeRange1,sizeRange2,responsePrice);
		
		ArtResponse ar = new ArtResponse();
		ar.setTotalPage(totalPage);
		ar.setList(list);
		
		Gson gson = new Gson();
		return gson.toJson(ar); 
	}
	
	
	@RequestMapping("/detailArt.do")
	public String detailArt(int memNo,int artNo) {
		String r = ""; 
		ArtInfoVo a = dao.detailArt(memNo,artNo);
		Gson gson = new Gson();
		r = gson.toJson(a);
		
		System.out.println("Controller의 detailArt 작동함");
		return r;
	}
	
	
	
	@RequestMapping("/detailArtHash.do")
	public String detailArtHash(String tag) {
		System.out.println("detailArtHash tag : " + tag);
		//String r = ""; 
		List<ArtInfoVo> list = dao.detailArtHash(tag);
		Gson gson = new Gson();
		
		
		System.out.println("Controller의 detailArtHash 작동함");
		return gson.toJson(list);
	}
	
	
	
}
