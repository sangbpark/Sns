package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.PostCardDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TimelineBO {

	private final PostBO postBO;
	private final CommentBO commentBO;
	private final LikeBO likeBO;
	
	public List<PostCardDTO> generatePostCardList() {
		List<PostCardDTO> timelineList = new ArrayList<>();
		
		List<PostEntity> postList = postBO.getPostListOderByIdDesc();
		if (postList.isEmpty()) {
			return timelineList;
		} else {
			for (PostEntity post : postList) {
				PostCardDTO timeline = PostCardDTO
						.builder()
						.postId(post.getId())
						.Name(post.getUser().getNickName())
						.imagePath(post.getImagePath())
						.content(post.getContent())
						.likeCount(likeBO.getLikeListByPostId(post.getId()).size())
						.comment(commentBO.getCommentListByPostIdJoinUser(post.getId()))
						.build();
				timelineList.add(timeline);
			}
		}
		return timelineList;
	}
}
