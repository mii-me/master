package com.art.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVo {
	
	//결제페이지, payment_tb
	private int payNo;
	private int memNo;
	private int artNo;
	private int payAmount;
	private String payValue;
	private Date payTime;
	
	
	//작품정보, art_info_tb
	private String artName;
	private String artTheme;
	private String artMaterial;
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
	
	//작품 판매 정보 art_sell_tb
	private String artSell;
	private int artTime;
	
	
	//회원정보, member_tb
	private String memName;
	private String memTel;
	private String memAddr;
	
	//경매장, auction_tb
	private String bidTop;
	
}
