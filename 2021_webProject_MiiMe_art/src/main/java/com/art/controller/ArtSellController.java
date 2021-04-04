package com.art.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.art.dao.ArtSellDao;
import com.art.vo.ArtInfoVo;
import com.art.vo.ArtResponse;
import com.google.gson.Gson;

/**
 * 작품 판매를 위한 작품 정보 등록, 수정, 삭제<br>
 * 등록한 작품을 판매여부를 부여해 판매시스템으로 넘어가는 일
 * 
 * @author 남혜진
 *
 */
@RestController
public class ArtSellController {

	// @페이징 처리는 미구현@
	// 한페이지에 보여질 상품개수
	public static int pageSIZE = 3;
	// 레코드 전체 개수
	public static int totalRecord = 0;
	// 전체 페이지 수
	public static int totalPage = 1;

	@Autowired
	private ArtSellDao dao;

	public void setDao(ArtSellDao dao) {
		this.dao = dao;
	}

	/**
	 * 남혜진 210318_작품정보 추가 art_info_tb 에 작품정보 insert 하는 일
	 * 
	 * @param vo 		작품정보가 담긴 Vo
	 * @param request	파일 사용을 위한 객체
	 * @return			성공여부
	 */
	@RequestMapping("/insertArtInfo.do")
	public int insertArtInfo(ArtInfoVo vo, HttpServletRequest request) {
		String path = request.getRealPath("art_pic");
		// System.out.println("path" + path);//test
		MultipartFile uploadFile = vo.getUploadFile();
		String artPic = uploadFile.getOriginalFilename();
		if (artPic != null && !artPic.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + artPic);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				System.err.println("예외발생 : " + e.getMessage());
			}
		}
		vo.setArtPic(artPic);
		int re = dao.insertArtInfo(vo);

		return re;
	}

	/**
	 * 남혜진_210320 작품정보수정,삭제,판매하기 기능을 가진 페이지에서 <br>
	 * 작품정보(체크테이블에도 존재)목록 불러오기 <br>
	 * listArtSellCheck.html 에서 art_sell_check_tb목록을 불러오는 일
	 * 
	 * @return
	 */
	@RequestMapping("/listArtSellCheck.do")
	public String findSellCheckList(int memNo, int pageNUM) {
		//PAGE 페이징 처리 나중에 하기
		totalRecord = dao.getTotalRecord(memNo);
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);
		
		// 시작 페이지, 끝 페이지 계산
		int start = (pageNUM - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		List<ArtInfoVo> list = dao.findSellCheckList(memNo, start, end);
		
		//List<ArtInfoVo> list = dao.findSellCheckList(memNo);
		ArtResponse art = new ArtResponse();
		
		art.setTotalPage(totalPage);
		art.setList(list);

		Gson gson = new Gson();
		return gson.toJson(art);
	}

	/**
	 * 남혜진 210320_작품정보 수정
	 * 
	 * @@ 문제 : 파일 수정시 이전 사진이 삭제가 안됨 //test
	 * @param a
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateArtInfo.do")
	public String updateArtInfo(ArtInfoVo a, HttpServletRequest request) {
		String oldFname = a.getArtPic();
		String path = request.getRealPath("art_pic");
		MultipartFile uploadFile = a.getUploadFile();
		String artPic = uploadFile.getOriginalFilename();
		if (artPic != null & !artPic.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + artPic);
				fos.write(data);
				fos.close();
				// insert할 때에는, vo에 setter를 사용해, fname을 맨 마지막에 설정하지만
				// update할 때에는, file이 수정될 수도, 수정되지 않을 수도 있기 때문에
				// if문 -> try문 안에서 설정한다
				a.setArtPic(artPic);
			} catch (Exception e) {
				System.err.println("예외발생 : " + e.getMessage());
			}
		}
		int re = dao.updateArtInfo(a);
		if (re == 1 && artPic != null && !artPic.equals("")) {
			File file = new File(path + "/" + oldFname);
			file.delete();
		}
		return re + "";
	}

	/**
	 * 남혜진 210320_판매 시작하기_정보테이블에는 존재, 체크테이블에서는 삭제, 판매중테이블에 추가 art_sell_tb 에 art_sell 컬럼의 값을
	 * sale로 insert, art_sell_check_tb에서 삭제 (art_info_tb에는 남아있다)
	 * 
	 * @param artNo
	 * @return
	 */
	@RequestMapping("/startArtSell.do")
	public int startArtSell(int artNo, int memNo) {
		int re1 = dao.insertArtSell(artNo, memNo);
		int re2 = dao.deleteArtSellCheck(artNo);
		return re1 + re2;
	}

	/**
	 * 남혜진 210320_작품정보 삭제_모든 테이블에 존재하지 않게 된다 art_info_tb, art_sell_check_tb 의 레코드 모두 삭제
	 * 
	 * @param artNo
	 * @return
	 */
	@RequestMapping("/deleteArtInfo.do")
	public int deleteArtInfo(int artNo) {
		int re1 = dao.deleteArtSellCheck(artNo);
		int re2 = dao.deleteArtInfo(artNo);
		return re1 + re2;
	}

	/**
	 * 남혜진 210322_판매중인 작품 목록 보기
	 * 
	 * @return
	 */
	@RequestMapping("/listArtSell.do")
	public String listArtSell(int memNo) {
		List<ArtInfoVo> list = dao.findArtSell(memNo);
 		Gson gson = new Gson();
		return gson.toJson(list);
	}

	/**
	 * 남혜진 210322_판매중인 작품 판매 취소하기
	 * 1. art_sell_tb에서 삭제
	 * 2. art_sell_check_tb로 다시 insert
	 * 
	 * @param artNo
	 * @return
	 */
	@RequestMapping("/cancleArtSell.do")
	public int cancleArtSell(int artNo, int memNo) {
		int re1 = dao.cancleArtSell(artNo);
		int re2 = dao.insertArtSellCheck(artNo, memNo);
		return re1 + re2;
	}

	/**
	 * 남혜진_포인트 조회
	 * 
	 * @param memNo
	 * @return
	 */
	@RequestMapping("/findArtistPoint.do")
	public Map<String, Object> findArtistPoint(int memNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		int point = dao.findArtistPoint(memNo);
		String artistName = dao.findArtistName(memNo);
		map.put("payPoint", point);
		map.put("artistName", artistName);
		return map;
	}
	
	/**
	 * 남혜진_포인트 환전
	 * 
	 * @param artPoint 포인트 잔액
	 * @param chgPoint 환전할 포인트
	 * @param memNo 회원번호
	 * @return
	 */
	
	/*
	@RequestMapping("/changePoint.do")
	public int updateArtistPoint(int artPoint, int chgPoint, int memNo) {
		int resultPoint = artPoint - chgPoint;
		System.out.println("1. 변경할 포인트 : " + resultPoint);
		int re = dao.updateArtistPoint(resultPoint, memNo);
		return re;
	}
	*/
	
}
