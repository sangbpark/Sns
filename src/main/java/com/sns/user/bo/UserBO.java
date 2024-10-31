package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	
	public boolean isDupilcateId(String loginId) {
		return userRepository.findByLoginId(loginId).isPresent();
	}
}
