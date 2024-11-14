package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	public int selectLikeListByPostId(int postId);
	
	public int selectLikeByPostIdAndUserId(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
//	public int selectLikeCountPostIdOrUserId(
//			@Param("postId")int postId, 
//			@Param("userId")Integer userId); 
	
	public int insertLike(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
	public int deleteLike(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
	public int deleteLikesByPostId(int postId);
}
