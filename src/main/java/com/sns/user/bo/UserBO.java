package com.sns.user.bo;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sns.common.EncryptUtils;
import com.sns.user.Enitity.UserEntity;
import com.sns.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserBO {
	private final UserRepository userRepository;
	
	public boolean isDupilcateId(String loginId) {
		return userRepository.findByLoginId(loginId).isPresent();
	}
	
	public UserEntity addUser(String loginId, String password, String name, String email) {
		String hashedPassword = EncryptUtils.md5(password);
		try {
			return userRepository.save(
	        		UserEntity.builder()
	        		.loginId(loginId)
	        		.password(hashedPassword)
	        		.name(name)
	        		.email(email)
	        		.build());
	    } catch (DataIntegrityViolationException e) {
	        return null;
	    }
	}
	
	public UserEntity getUserEntityByLoginIdAndPassword(
			String loginId, String password) {
		String hashedPassword = EncryptUtils.md5(password);
		return userRepository.findByLoginIdAndPassword(loginId, hashedPassword).orElse(null);
	}
}
