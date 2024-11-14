package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostCardDTO {
	private int postId;
	private int userId;
	private String Name;
	private String imagePath;
	private int likeCount;
	private String content;
	private List<CommentDTO> comment;	
	
	// 좋아요 여부
	private boolean filledLike;
}
