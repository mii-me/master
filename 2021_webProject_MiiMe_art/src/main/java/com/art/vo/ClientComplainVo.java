package com.art.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientComplainVo {//by현익 / 고객센터 VO / 210319
	private int memNo;
	private int comPwd, comNo;
	private String comTitle, comMsg;
	private String comDate, comImg;
	private MultipartFile uploadFile;
}
