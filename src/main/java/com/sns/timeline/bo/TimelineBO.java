package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.Timeline;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public List<Timeline> getTimelineList() {
		List<Timeline> timelineList = new ArrayList<>();
		
		List<PostEntity> postList = postBO.getPostOderByIdDesc();
		if (postList.isEmpty()) {
			return timelineList;
		} else {
			for (PostEntity post : postList) {
				Timeline timeline = new Timeline();
				timeline.setPostId(post.getId());
				timeline.setName(post.getUser().getNickName());
				timeline.setImagePath(post.getImagePath());
				timeline.setLikeCount(likeBO.getLikeListByPostId(post.getId()).size());
				timeline.setComment(commentBO.getCommentListByPostId(post.getId()));
				timelineList.add(timeline);
			}
		}
		return timelineList;
	}
}
