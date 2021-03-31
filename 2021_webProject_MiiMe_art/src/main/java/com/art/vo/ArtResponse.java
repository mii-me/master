package com.art.vo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtResponse {
	//페이지수, 상품 리스트
	private int totalPage;
	private List<ArtInfoVo> list;
}
