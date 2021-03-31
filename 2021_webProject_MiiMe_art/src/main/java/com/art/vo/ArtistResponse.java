package com.art.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistResponse {
	private int totalPage; // 작가목록 전체 페이지 수
	private int totalPageArt; // 작품목록 전체 페이지 수
	private int totalPageCmt; // 기대평목록 전체 페이지 수
	private List<ArtistInfoVo> list; // 작가목록, 작품목록
	private List<ArtistCommentVo> listCmt; // 기대평목록
}
