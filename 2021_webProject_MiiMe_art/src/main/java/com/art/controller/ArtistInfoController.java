package com.art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dao.ArtistInfoDao;
import com.art.vo.ArtistCommentVo;
import com.art.vo.ArtistInfoVo;
import com.art.vo.ArtistResponse;
import com.google.gson.Gson;

@RestController // 각각의 메소드에 ResponseBody annotation 자동삽입
public class ArtistInfoController {
	
	public static int pageSIZE = 9; // 한페이지에 보여줄 레코드 수(작가목록, 작품목록)
	public static int cmtSIZE = 5; // 한페이지에 보여줄 코멘트 수(작가 한줄기대평)
	public static int totalRecord = 0; // 전체 레코드 수
	public static int totalPage = 1; // 전체 페이지 수
	public static int totalPageArt = 1; // 전체 페이지 수
	public static int totalPageCmt = 1; // 전체 페이지 수
	
	@Autowired
	private ArtistInfoDao dao;
	public void setDao(ArtistInfoDao dao) {
		this.dao = dao;
	}
	
	
	// ========================= 최상위 메인페이지 진입 =========================
	
	/**
	 * 미미 메인페이지 진입 시 홈에 보이는 최신순 작품 3개 슬라이드
	 * @author 정소윤
	 * @return 최신순 작품 3개 정보
	 */
	@RequestMapping("/mainArt.do")
	public String mainArt() {
		String r = "";
		List<ArtistInfoVo> list = dao.mainArt();
		Gson gson = new Gson();
		r = gson.toJson(list);
		
		return r;
	}//topArtistInfo
	
	// ========================= 최상위 메인페이지 END =========================
	
	
	
	
	// ========================= 작가소개 메인페이지 진입 =========================
	
	/**
	 * 작가소개 메인 진입 시 상단에 보이는 인기작가 Top3 슬라이드
	 * @author 정소윤
	 * @return 인기작가 Top3 작가 정보
	 */
	@RequestMapping("/topArtistInfo.do")
	public String topArtistInfo() {
		String r = "";
		List<ArtistInfoVo> list = dao.topArtistInfo();
		Gson gson = new Gson();
		r = gson.toJson(list);
		
		return r;
	}//topArtistInfo
	
	
	/**
	 * 작가소개 메인 진입 시 보이는 작가목록
	 * 최신순 정렬 작가목록
	 * @author 정소윤
	 * @param pageNUM 작가목록에서 선택한 페이지번호
	 * @param keyword 검색어
	 * @return 작가목록(최신순 정렬 기준)
	 */
	@RequestMapping("/listArtistInfo.do")
	public String listArtistInfo(int pageNUM, String keyword, String consonant, String sort) {
		System.out.println("1. 작가목록 조회 Controller 작동: pageNUM "+pageNUM+"/keyword "+keyword+"/consonant "+consonant+"/sort "+sort);
		
		// 페이징 처리
		totalRecord = dao.getTotalArtist(keyword, consonant, sort);
		totalPage = (int)Math.ceil(totalRecord / (double)pageSIZE);
		
//		System.out.println("[Controller]선택한 페이지번호: "+pageNUM); // 페이지 번호 확인
//		System.out.println("[Controller]검색어: "+keyword); // 검색어 확인
//		System.out.println("[Controller]선택한 자음: "+consonant); // 가나다 목록 확인
		
		int start = (pageNUM-1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		List<ArtistInfoVo> list = dao.listArtistInfo(start, end, keyword, consonant, sort);
		
		ArtistResponse r = new ArtistResponse();
		r.setTotalPage(totalPage);
		r.setList(list);
		
		Gson gson = new Gson();
		return gson.toJson(r);
	}//listArtistInfo
	
	// ========================= 작가소개 메인페이지 END =========================
	
	
	
	// ========================= 특정 작가 클릭 시 해당 작가의 상세프로필 페이지 진입 =========================
	
	/**
	 * 해당 작가의 상세프로필 상단에 보이는 작가 정보
	 * @author 정소윤
	 * @param memNo 해당 작가의 회원번호
	 * @return 해당 작가의 정보(ARTIST_INFO_TB)
	 */
	@RequestMapping("/detailArtistInfo.do")
	public String selectArtistInfo(int memNo) {
		System.out.println("1. 작가프로필 조회 Controller 작동: memNo "+memNo);
		
		String r = "";
		ArtistInfoVo a = dao.selectArtistInfo(memNo);
		
		Gson gson = new Gson();
		r = gson.toJson(a);
		
		return r;
	}//selectArtistInfo
	
	
	/**
	 * 해당 작가의 상세프로필 중단에 보이는 작품 목록
	 * @author 정소윤
	 * @param memNo 해당 작가의 회원번호
	 * @param pageNUM 작품목록에서 선택한 페이지번호
	 * @return 해당 작가의 작품 정보
	 */
	@RequestMapping("/detailArtistInfoArt.do")
	public String listArtistInfoArt(int memNo, int pageNUM) {
//		System.out.println("1. 작품목록 조회 Controller 작동: memNo "+memNo+"/pageNUM "+pageNUM);
		
		// 페이징 처리
		totalRecord = dao.getTotalArtistArt(memNo);
		totalPageArt = (int)Math.ceil(totalRecord / (double)pageSIZE);
		
		int start = (pageNUM-1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;

		List<ArtistInfoVo> list = dao.listArtistInfoArt(memNo, start, end);
		
		ArtistResponse r = new ArtistResponse();
		r.setTotalPageArt(totalPageArt);
		r.setList(list);
		
		Gson gson = new Gson();
		return gson.toJson(r);
	}//listArtistInfo

	
	/**
	 * 해당 작가의 상세프로필 하단에 보이는 작가 기대평
	 * @author 정소윤
	 * @param memNo 해당 작가의 회원번호
	 * @param pageNUM 코멘트게시판에서 선택한 페이지번호
	 * @return 해당 작가의 한줄기대평 목록
	 */
	@RequestMapping("/detailArtistComment.do")
	public String listArtistComment(int memNo, int pageNUM) {
//		System.out.println("1. 작가기대평 조회 Controller 작동: memNo "+memNo+"/pageNUM "+pageNUM);
		
		// 페이징 처리
		totalRecord = dao.getTotalArtistComment(memNo);
		totalPageCmt = (int)Math.ceil(totalRecord / (double)cmtSIZE);
		
		int start = (pageNUM-1) * cmtSIZE + 1;
		int end = start + cmtSIZE - 1;
		
		List<ArtistCommentVo> list = dao.listArtistComment(memNo, start, end);
		
		ArtistResponse r = new ArtistResponse();
		r.setTotalPageCmt(totalPageCmt);
		r.setListCmt(list);
		
		Gson gson = new Gson();
		return gson.toJson(r);
	}//listArtistComment

	
	/**
	 * 작가 기대평 등록 시 해당 테이블에 insert
	 * @author 정소윤
	 * @param ac ArtistCommentVo, 작가기대평 테이블에 들어갈 값
	 * @return 작가 기대평 등록
	 */
	@RequestMapping("/insertArtistComment.do")
	public String insertArtistComment(ArtistCommentVo ac) {
//		System.out.println("1. 작가기대평 등록 Controller 작동: ac "+ac);
		
		int re = dao.insertArtistComment(ac);
		return re+"";
	}//insertArtistComment
	
	
	/**
	 * 작가 기대평 삭제 시 해당 테이블에서 delete
	 * @author 정소윤
	 * @param artistCmtNo 삭제할 코멘트 번호
	 * @return 
	 */
	@RequestMapping("/deleteArtistComment.do")
	public String deleteArtistComment(int artistCmtNo) {
		System.out.println("1. 작가기대평 삭제 Controller 작동: artistCmtNo "+artistCmtNo);
		
		int re = dao.deleteArtistComment(artistCmtNo);
		return re+"";
	}//deleteArtistComment
	
	
	
	// ========================= 특정 작가 클릭 시 해당 작가의 상세프로필 페이지 END =========================
	
	
}//class