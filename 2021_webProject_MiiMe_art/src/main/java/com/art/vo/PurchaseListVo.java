package com.art.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseListVo {//by현익 / 배송지관련 VO / 210319
	private int memNo, artNo, payNo, payAmount;
	private String artPic, payTime, payValue, artName;
}
