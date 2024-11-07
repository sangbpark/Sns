package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManager;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManager fileManger;
	
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
