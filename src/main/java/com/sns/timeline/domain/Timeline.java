package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentDTO;

import lombok.Data;

@Data
public class Timeline {
	private int postId;
	private String Name;
	private String imagePath;
	private int likeCount;
	private String content;
	private List<CommentDTO> comment;	 
}
