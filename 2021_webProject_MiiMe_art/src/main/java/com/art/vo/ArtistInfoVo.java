package com.art.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistInfoVo {
	// 공통 회원정보
	private String memId;
	private String memPwd;
	private String memName;
	private String memRrn;
	private String memGender;
	private String memTel;
	private String memEmail;
	private String memAddr;
	private String memIsArtist;
	private Date memSignDate;
	
	// 작가 프로필정보
	private int memNo;
	private String artistName;
	private String artistPic;
	private String artistIntro;
	private String artistDetail;
	private String artistCareer;
	private MultipartFile uploadImg;
	
	// 작가 포인트
	private int payPoint;
	
	// 작가프로필 작품목록 작품정보
	private int artNo;
	private String artPic;
	private String artName;
	private String artTag1;
	
}
