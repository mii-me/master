package com.art.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtInfoVo {
	private int memNo;
	private int artNo;
	private String artName;
	private String artMaterial;
	private String artTheme;
	private String artSize1;
	private String artSize2;
	private int artYear;
	private String artPic;
	private String artExp;
	private String artTag1;
	private String artTag2;
	private String artTag3;
	private int aucStart;
	private int aucBid;
	private int aucBuy;
	
	private String artistName;
	private String artistIntro;
	private String artistCareer;
	private String artistPic;
	
	private String artSell;
	
	
	private String memId;
	private String memPwd;
	private String memName;

	//file추가
	private MultipartFile uploadFile;
}
