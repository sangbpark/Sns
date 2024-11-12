package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;

@Mapper
public interface CommentMapper {
	public List<Comment> selectCommentByPostId(int postId);
	public List<CommentDTO> selectCommentByPostIdJoinUser(int postId);
	
	public int insertComment(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content);
	
	public int deleteCommentById(int commentId);
}
