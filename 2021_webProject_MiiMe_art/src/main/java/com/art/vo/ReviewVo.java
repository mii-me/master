package com.art.vo;



import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVo {
	private int reviewNo;
	private String reviewTime;
	private String reviewImage;
	private int reviewLike;
	private String reviewText;	
	private int memNo;
	private int artNo;
	
	//빌려온거 
	private int payAmount;
	private String artTheme, artName, artTag1, artTag2, artTag3, artistName, atMemNo;
	
	private MultipartFile uploadFile; //업로드 된 파일을 받을 자료형 

}

