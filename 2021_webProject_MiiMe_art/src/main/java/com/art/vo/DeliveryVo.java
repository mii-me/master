package com.art.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryVo {//by현익 / 배송지관련 VO / 210319
	private int delNo;
	private int memNo;
	private String delName, delTel;
	private String delAddr, delMsg;
}
