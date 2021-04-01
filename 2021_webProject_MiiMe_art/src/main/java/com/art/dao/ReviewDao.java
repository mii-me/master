package com.art.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.art.db.DBManager;
import com.art.vo.ReviewVo;



@Repository
public class ReviewDao {
	
	public int getTotalRecord(String keyword, String searchField) {
		return DBManager.getTotalRecord(keyword, searchField);
	}
	
	public List<ReviewVo> findAll( String keyword, String searchField,String orderField){
		System.out.println("dao동작함");
		return DBManager.findAll( keyword, searchField, orderField);
	}
	
	public List<ReviewVo> findSearchAll(String keyword, String searchField, String orderField){
		System.out.println("dao동작함");
		return DBManager.findSearchAll( keyword, searchField, orderField);
	}
	
	
	public List<ReviewVo> findThemeAll( String keyword, String orderField){
		System.out.println("dao동작함");
		return DBManager.findThemeAll(keyword, orderField);
	}
	
	public List<ReviewVo> findTagAll(String keyword, String orderField){
		System.out.println("dao동작함");
		return DBManager.findTagAll(keyword, orderField);
	}
	
	public List<ReviewVo> findRealTimeAll(){
		System.out.println("dao동작함");
		return DBManager.findRealTimeAll();
	}
	public List<ReviewVo> findRealTimeMost3LikeAll(){
		System.out.println("dao동작함");
		return DBManager.findRealTimeMost3LikeAll();
	}


	public int insertReview(ReviewVo r) {
		// TODO Auto-generated method stub
		return DBManager.insert(r);
	}

	public int insertLikeReview(int reviewNo) {
		System.out.println("insertLike 동작");
		return DBManager.insertLikeReview(reviewNo);
	}

	public int deleteLikeReview(int reviewNo) {
		System.out.println("deleteLike 동작");
		return DBManager.deleteLikeReview(reviewNo);
	}

}












