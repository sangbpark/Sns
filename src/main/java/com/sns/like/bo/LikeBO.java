package com.sns.like.bo;

import java.util.OptionalInt;

import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeBO {
	private final LikeMapper likeMapper;
	
	public int getLikeListByPostId(int postId) {
		return likeMapper.selectLikeListByPostId(postId);
	};
	
	public int getLikeByPostIdAndUserId(int postId, int userId) {
		return likeMapper.selectLikeByPostIdAndUserId(postId, userId);
	}
	
	public boolean addLike(int postId, int userId) {
		if (likeMapper.insertLike(postId, userId) > 0) {
			return true;
		} 
		return false;
	}
	
	public boolean deleteLike(int postId, int userId) {
		if (likeMapper.deleteLike(postId, userId) > 0) {
			return true;
		} 
		return false;
	}
	
	public boolean deleteLikesByPostId(int postId) {
		if (likeMapper.deleteLikesByPostId(postId) > 0) {
			return true;
		} 
		return false;
	}
	
	public boolean toggleLike(int postId, int userId) {
		if (likeMapper.selectLikeByPostIdAndUserId(postId, userId) == 0) {
			if (likeMapper.insertLike(postId, userId) > 0) {
				return true;
			} 
			return false;
		} else {
			if (likeMapper.deleteLike(postId, userId) > 0) {
				return true;
			} 
			return false;
		}
	}
	
	public boolean isFilled (int postId, OptionalInt userId) {
		if (userId.isPresent()) {
			if (likeMapper.selectLikeByPostIdAndUserId(postId, userId.getAsInt()) != 0 ) {
				return true;
			}
		}
		return false;
	}
}
