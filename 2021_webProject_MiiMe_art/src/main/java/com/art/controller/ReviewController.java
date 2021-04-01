package com.art.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.art.dao.ReviewDao;
import com.art.vo.ReviewResponse;
import com.art.vo.ReviewVo;
import com.google.gson.Gson;

@RestController
public class ReviewController {

	@Autowired
	private ReviewDao dao;

	public void setDao(ReviewDao dao) {
		this.dao = dao;
	}
	
	static int totalRecord;

	@RequestMapping("/insertReviewProto.do")
	public String insertReviewProto(ReviewVo r, HttpServletRequest request) {
		String path = request.getRealPath("img");
		System.out.println("path:" + path);
		MultipartFile uploadFile = r.getUploadFile();

		String fname = uploadFile.getOriginalFilename();
//		System.out.println(fname);

		if (fname != null && !fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
//				System.out.println(fos);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				System.out.println("예외발생:" + e.getMessage());
			}

		}

		r.setReviewImage(fname);
		int re = dao.insertReview(r);

		System.out.println(r);
		return re + "";
	}

	@RequestMapping("/detailRealTimeReviewProto.do")
	public String detailRealTimeReviewProto() { // 검색, 정렬 기능 없으니까 keyword, searchField, orderField 삭제

		totalRecord = dao.getTotalRecord("", ""); // 모두 값이 없으니까 null로 보낸다


		List<ReviewVo> list = dao.findRealTimeAll(); //

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		System.out.println(list);
		System.out.println(gson.toJson(r));
		return gson.toJson(r);
	}

	@RequestMapping("/detailRealTimeReviewMost3LikeProto.do")
	public String detailRealTimeReviewMost3LikeProto() { // 검색, 정렬 기능 없으니까 keyword, searchField, orderField 삭제
		totalRecord = dao.getTotalRecord("", ""); // 모두 값이 없으니까 null로 보낸다
		List<ReviewVo> list = dao.findRealTimeMost3LikeAll(); //

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		System.out.println(list);
		System.out.println(gson.toJson(r));
		return gson.toJson(r);
	}

	@RequestMapping("/detailSearchReviewProto.do")
	public String detailReviewProto( String keyword, String searchField, String orderField) {
		System.out.println("keyword:" + keyword);
		System.out.println("searchField:" + searchField);
		System.out.println("orderField:" + orderField);

		totalRecord = dao.getTotalRecord(keyword, searchField); // op는 없으니까 null로 보낸다

		List<ReviewVo> list = dao.findSearchAll(keyword, searchField, orderField); // op는 없으니까 뺌

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		return gson.toJson(r);
	}

	@RequestMapping("/detailThemeReviewProto.do")
	public String detailThemeReviewProto(String keyword, String orderField) {
		System.out.println("keyword:" + keyword);
		System.out.println("orderField:" + orderField);

		totalRecord = dao.getTotalRecord(keyword, ""); // op는 없으니까 null로 보낸다
		List<ReviewVo> list = dao.findThemeAll(keyword, orderField); // op는 없으니까 뺌

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		return gson.toJson(r);
	}

	@RequestMapping("/detailTagReviewProto.do")
	public String detailTagReviewProto( String keyword, String orderField) {
		System.out.println("keyword:" + keyword);
		System.out.println("orderField:" + orderField);

		totalRecord = dao.getTotalRecord(keyword, ""); // op는 없으니까 null로 보낸다
		List<ReviewVo> list = dao.findTagAll(keyword, orderField); // op는 없으니까 뺌

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		return gson.toJson(r);
	}

	@RequestMapping("/insertLikeReviewProto.do")
	public int detailTagReviewProto(int reviewNo) {
		System.out.println("reviewNo:" + reviewNo);
		int re = dao.insertLikeReview(reviewNo); 
		return re;
	}
	
	@RequestMapping("/deleteLikeReviewProto.do")
	public int deleteLikeReviewProto(int reviewNo) {
		System.out.println("reviewNo:" + reviewNo);
		int re = dao.deleteLikeReview(reviewNo); 
		return re;
	}
}
