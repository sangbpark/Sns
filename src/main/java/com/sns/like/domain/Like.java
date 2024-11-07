package com.sns.like.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Like {
	private int postId;
	private int userId;
	private LocalDateTime updatedAt;
}
