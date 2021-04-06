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

	/**
	 * 이용자가 작성한 글을 서버에 등록하기 위한 메소드 
	 * @param r
	 * @param request
	 */
	@RequestMapping("/insertReviewProto.do")
	public String insertReviewProto(ReviewVo r, HttpServletRequest request) {
		String path = request.getRealPath("img");
//		System.out.println("path:" + path);
		MultipartFile uploadFile = r.getUploadFile();

		String fname = uploadFile.getOriginalFilename();

		if (fname != null && !fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
//				System.out.println("예외발생:" + e.getMessage());
			}
		}

		r.setReviewImage(fname);
		int re = dao.insertReview(r);

		return re + "";
	}

	
	/**
	 * 실시간 후기를 받아올 메소드
	 * @return
	 */
	@RequestMapping("/detailRealTimeReviewProto.do")
	public String detailRealTimeReviewProto() { // 검색, 정렬 기능 없으니까 keyword, searchField, orderField 삭제

		totalRecord = dao.getTotalRecord("", ""); // 모두 값이 없으니까 null로 보낸다

		List<ReviewVo> list = dao.findRealTimeAll();

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
//		System.out.println(list);
//		System.out.println(gson.toJson(r));
		return gson.toJson(r);
	}

	/**
	 * 실시간 후기 중 좋아요 상위 3개만 뽑아올 메소드 
	 * @return
	 */
	@RequestMapping("/detailRealTimeReviewMost3LikeProto.do")
	public String detailRealTimeReviewMost3LikeProto() { // 검색, 정렬 기능 없으니까 keyword, searchField, orderField 삭제
		
		totalRecord = dao.getTotalRecord("", ""); // 모두 값이 없으니까 null로 보낸다
		
		List<ReviewVo> list = dao.findRealTimeMost3LikeAll();

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
//		System.out.println(list);
//		System.out.println(gson.toJson(r));
		return gson.toJson(r);
	}

	/**
	 * 사용자가 키워드를 이용하여 검색시 그 키워드에 해당하는 후기를 불러오는 메소드 
	 * @param keyword: 사용자가 입력한 키워드 
	 * @param searchField: 그 키워드가 속하는 필드 
	 * @param orderField: 정렬 기준 
	 * @return
	 */
	@RequestMapping("/detailSearchReviewProto.do")
	public String detailReviewProto(String keyword, String searchField, String orderField) { //패러미터로 키워드, 서치필드, 오더필드 받기 
//		System.out.println("keyword:" + keyword);
//		System.out.println("searchField:" + searchField);
//		System.out.println("orderField:" + orderField);

		totalRecord = dao.getTotalRecord(keyword, searchField);

		List<ReviewVo> list = dao.findSearchAll(keyword, searchField, orderField);

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		return gson.toJson(r);
	}

	/**
	 * 사용자가 테마 키워드를 클릭하여 리뷰에 진입 시 그 키워드에 해당하는 후기를 불러오는 메소드 
	 * @param keyword: 테마 키워드 
	 * @param orderField: 정렬 기준(=테마 분류)
	 * @return
	 */
	@RequestMapping("/detailThemeReviewProto.do")
	public String detailThemeReviewProto(String keyword, String orderField) {
//		System.out.println("keyword:" + keyword);
//		System.out.println("orderField:" + orderField);

		totalRecord = dao.getTotalRecord(keyword, "");
		List<ReviewVo> list = dao.findThemeAll(keyword, orderField);

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		return gson.toJson(r);
	}

	/**
	 * 사용자가 태그 키워드를 클릭하여 리뷰에 진입 시 그 키워드에 해당하는 후기를 불러오는 메소드 
	 * @param keyword: 태그 키워드 
	 * @param orderField: 정렬 기준(=태그 분류)
	 * @return
	 */
	@RequestMapping("/detailTagReviewProto.do")
	public String detailTagReviewProto( String keyword, String orderField) {
//		System.out.println("keyword:" + keyword);
//		System.out.println("orderField:" + orderField);

		totalRecord = dao.getTotalRecord(keyword, ""); // op는 없으니까 null로 보낸다
		List<ReviewVo> list = dao.findTagAll(keyword, orderField); // op는 없으니까 뺌

		/////// java to JSON을 위하여 GSON 사용
		ReviewResponse r = new ReviewResponse();
		r.setList(list);

		Gson gson = new Gson();
		return gson.toJson(r);
	}

	/**
	 * 사용자가 좋아요를 누르면 서버에 좋아요 갯수를 1개 늘려주는 메소드 
	 * @param reviewNo: pk, 후기번호
	 * @return
	 */
	@RequestMapping("/insertLikeReviewProto.do")
	public int detailTagReviewProto(int reviewNo) { //패러미터로 그 후기가 가지는 고유 번호를 받는다 
//		System.out.println("reviewNo:" + reviewNo);
		int re = dao.insertLikeReview(reviewNo); 
		return re;
	}
	
	/**
	 * 사용자가 좋아요를 한 번 더 누르면 서버에 좋아요 갯수를 1개 줄여주는 메소드 
	 * @param reviewNo: pk, 후기번호
	 * @return
	 */
	@RequestMapping("/deleteLikeReviewProto.do")
	public int deleteLikeReviewProto(int reviewNo) { //패러미터로 그 후기가 가지는 고유 번호를 받는다 
//		System.out.println("reviewNo:" + reviewNo);
		int re = dao.deleteLikeReview(reviewNo); 
		return re;
	}
}
