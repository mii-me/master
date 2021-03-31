package com.art.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistCommentVo {
	private int artistCmtNo;
	private int memNo;
	private int memNoB;
	private String artistCmt;
	private String artistCmtDate;
	
	private String memName; // 작성자
}
