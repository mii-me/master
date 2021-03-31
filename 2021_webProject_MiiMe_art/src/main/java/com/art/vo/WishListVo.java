package com.art.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListVo {//by현익 / 찜목록 VO / 210323
	private int memNo, artNo, aucBid, aucBuy, artistNo;
	private String artPic, artName, tag1;
}
