package com.sns.post.bo;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManager;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostBO {
	private final PostRepository postRepository;
	private final FileManager fileManger;
	
	public List<PostEntity> getPostListOderByIdDesc() {
		return postRepository.findAllByOrderByIdDesc();
	};
	
	
	public PostEntity addPost(int userId, String content, MultipartFile file , String loginId) {
		String imagePath = fileManger.uploadFile(file, loginId);
		try {
			return postRepository.save(PostEntity
						.builder()
						.userId(userId)
						.content(content)
						.imagePath(imagePath)
						.build());
		} catch (DataIntegrityViolationException e) {
	        return null;
	    }
	}
}
