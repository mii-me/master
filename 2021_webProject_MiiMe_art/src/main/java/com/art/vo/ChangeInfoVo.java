package com.art.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeInfoVo {//by현익 / 회원정보수정 VO / 210319
	private int memNo;
	private String memId, memName, memTel, memEmail, memPwd, memIsartist;
	private String artistName, artistPic, artistIntro, artistCareer, artistDetail;
	private MultipartFile uploadFile;
}
