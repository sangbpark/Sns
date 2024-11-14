package com.sns.comment.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentBO {
	private final CommentMapper commentMapper;
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentMapper.selectCommentByPostId(postId);
	};
	
	public List<CommentDTO> getCommentListByPostIdJoinUser(int postId) {
		return commentMapper.selectCommentByPostIdJoinUser(postId);
	};
	
	public boolean addComment(int userId, int postId, String content) {
		if (commentMapper.insertComment(userId, postId, content) > 0) {
			return true;
		};
		return false;
	}
	
	public boolean deleteCommentById(int commentId) {
		if (commentMapper.deleteCommentById(commentId) > 0) {
			return true;
		}
		return false;
	};
	
	public boolean deleteCommentsByPostId(int postId) {
		if (commentMapper.deleteCommentsByPostId(postId) > 0) {
			return true;
		}
		return false;
	};
}
