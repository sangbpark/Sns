package com.sns.comment.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	private int id;
	private int userId;
	private String nickName;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
