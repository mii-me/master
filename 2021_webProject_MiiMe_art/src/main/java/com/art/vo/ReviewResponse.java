package com.art.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
	
	private List<ReviewVo> list;

	public List<ReviewVo> getList() {
		return list;
	}
	public void setList(List<ReviewVo> list) {
		this.list = list;
	}
}
