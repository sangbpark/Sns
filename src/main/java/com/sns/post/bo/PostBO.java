package com.sns.post.bo;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManager;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostBO {
	private final PostRepository postRepository;
	private final CommentBO commentBO;
	private final LikeBO likeBO;
	private final FileManager fileManger;
	
	public List<PostEntity> getPostListOderByIdDesc() {
		return postRepository.findAllByOrderByIdDesc();
	};
	
	public boolean addPost(int userId, String content, MultipartFile file , String loginId) {
		String imagePath = null;
		if (file != null) {
			imagePath = fileManger.uploadFile(file, loginId);
		}
		try {
			postRepository.save(PostEntity
						.builder()
						.userId(userId)
						.content(content)
						.imagePath(imagePath)
						.build());
			return true;
		} catch (DataIntegrityViolationException e) {
	        return false;
	    }
	}
	
	@Transactional
	public boolean deletePostByPostIdUserId(int postId, int userId) {
		PostEntity post = postRepository.findByIdAndUserId(postId, userId).orElse(null);
		if (post != null) {
			commentBO.deleteCommentsByPostId(postId);
			likeBO.deleteLikesByPostId(postId);
			postRepository.delete(post); // id(pk)로 삭제해줌
			
			if (post.getImagePath() != null) {
				fileManger.deleteFile(post.getImagePath());
			}
			return true;
		}
		log.info("[포스트 포스트삭제 실패]");
		return false;
	}
}
