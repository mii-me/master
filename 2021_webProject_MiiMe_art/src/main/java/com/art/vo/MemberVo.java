package com.art.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {
	private int memNo;
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
}