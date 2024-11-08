package com.sns.like.domain;

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
public class Like {
	private int postId;
	private int userId;
	private LocalDateTime updatedAt;
}
