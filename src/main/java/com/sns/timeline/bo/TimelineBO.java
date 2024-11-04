package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;
import com.sns.like.mapper.LikeMapper;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import com.sns.timeline.domain.Timeline;

@Service
public class TimelineBO {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private LikeMapper likeMapper;
	
	public List<Timeline> getTimelineList() {
		List<Timeline> timelineList = new ArrayList<>();
		
		List<PostEntity> postList = postRepository.findAllByOrderByIdDesc();
		if (postList.isEmpty()) {
			return timelineList;
		} else {
			for (PostEntity post : postList) {
				Timeline timeline = new Timeline();
				timeline.setPostId(post.getId());
				timeline.setName(post.getUser().getNickName());
				timeline.setImagePath(post.getImagePath());
				timeline.setLikeCount(likeMapper.selectLikeListByPostId(post.getId()).size());
				timeline.setComment(commentMapper.selectCommentByPostId(post.getId()));
				timelineList.add(timeline);
			}
		}
		return timelineList;
	}
}
