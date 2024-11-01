package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.Enitity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	
	public boolean isDupilcateId(String loginId) {
		return userRepository.findByLoginId(loginId).isPresent();
	}
	
	public UserEntity addUser(String loginId, String password, String name, String email) {
	    
		try {
			return userRepository.save(
	        		UserEntity.builder()
	        		.loginId(loginId)
	        		.password(password)
	        		.name(name)
	        		.email(email)
	        		.build());
	    } catch (org.springframework.dao.DataIntegrityViolationException e) {
	        return null;
	    }
	}
}
