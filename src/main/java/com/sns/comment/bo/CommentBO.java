package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentRepository;
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentRepository.selectCommentByPostId(postId);
	};
}
