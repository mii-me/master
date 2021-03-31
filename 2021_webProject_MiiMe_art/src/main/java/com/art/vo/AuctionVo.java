package com.art.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionVo {
	
	//경매장 정보, payment_tb
	private int aucNo;
	private int artNo;
	private int bidCount;
	
	private String memName;
	
	//작품 정보, art_info_tb
	private int memNo;
	private String artName;
	private String artMaterial;
	private String artTheme;
	private int artSize1;
	private int artSize2;
	private int artYear;
	private String artPic;
	private String artExp;
	private String artTag1;
	private String artTag2;
	private String artTag3;
	private int aucStart;
	private int aucBid;
	private int aucBuy;
	
	//작품 판매 art_sell_tb
	private String artSell;
	private int artTime;
	
	//작가 정보 artist_info_tb
	private String artistName;
	
}
