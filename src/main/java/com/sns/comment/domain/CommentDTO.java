package com.sns.comment.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
	private int id;
	private int userId;
	private String nickName;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
