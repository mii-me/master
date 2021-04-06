package com.art.controller;

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.art.dao.JoinDao;
import com.art.vo.ArtistInfoVo;
import com.art.vo.MemberVo;

@RestController
public class JoinController {
	
	@Autowired
	private JoinDao dao;
	public void setDao(JoinDao dao) {
		this.dao = dao;
	}
	
	
	/**
	 * 회원가입: 일반회원(B)일 경우 member_tb에 insert
	 * @author 정소윤
	 * @param m 데이터 내용
	 */
	@RequestMapping("/insertMember.do")
	public String insertMember(MemberVo m) {
		int re = dao.insertMember(m);
		
		return re+"";
	}//insertMember
	
	
	/**
	 * 회원가입: 작가회원(S)일 경우 member_tb과 artist_info_tb에 동시에 insert
	 * @author 정소윤
	 * @param m 데이터 내용
	 */
	@RequestMapping("/insertArtistInfo.do")
	public String insertArtistInfo(ArtistInfoVo a, HttpServletRequest request) {
		String path = request.getRealPath("artist_pic");
//		System.out.println("path: "+path); // 경로 출력
		
		MultipartFile uploadImg = a.getUploadImg();
		String artistPic = uploadImg.getOriginalFilename();
		
		if(artistPic!=null && !artistPic.equals("")) { // 업로드한 사진이 있다면
			try {
				byte[] data = uploadImg.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+artistPic);
				fos.write(data);
				fos.close();
				
			}catch(Exception e) {
//				System.out.println("예외발생: "+e.getMessage());
			}
		}//if
		
		a.setArtistPic(artistPic);
		int re = dao.insertArtistInfo(a);
		
//		System.out.println(a);
		
		return re+"";
	}//insertArtistInfo
	
	
	
	/**
	 * 로그인 시 회원정보 테이블과 비교
	 * @author 정소윤
	 * @param memId
	 * @param memPwd
	 */
	@RequestMapping("/loginOK.do")
	public MemberVo selectMember(String memId, String memPwd) {
//		System.out.println("loginOK.do => 1. 로그인 Controller 작동: memId "+memId);
		
		// id를 이용해 테이블 검색
		MemberVo m = dao.selectMember(memId); //#{memId}
		return m;
	} 

}
