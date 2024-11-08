package com.sns.user;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.ajaxresult.AjaxResultBO;
import com.sns.ajaxresult.NomalResult;
import com.sns.ajaxresult.ResultParameter;
import com.sns.ajaxresult.UserAuthorityResult;
import com.sns.user.Enitity.UserEntity;
import com.sns.user.bo.UserBO;
import com.sns.user.dto.UserSimple;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {
	private final UserBO userBO;
	
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(
			@RequestParam("loginId") String loginId) {
		
		boolean isDuplicateId = userBO.isDupilcateId(loginId);
		
		return new AjaxResultBO(new NomalResult())
				.getResult(new ResultParameter(isDuplicateId));
	}
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		boolean isSuccess = userBO.addUser(loginId, password, name, email);
		return new AjaxResultBO(new NomalResult())
				.getResult(new ResultParameter(isSuccess)
				.withMessage("회원가입에 성공했습니다."));
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		// db select
		UserEntity user = userBO.getUserEntityByLoginIdAndPassword(loginId, password);
		boolean isSuccess = false;
		if (user != null) {
			UserSimple userS = UserSimple
					.builder()
					.userId(user.getId())
					.userLoginId(user.getLoginId())
					.userName(user.getNickName())
					.build();
			session.setAttribute("userSimple", userS);
			isSuccess = true;
		}
	
		return new AjaxResultBO(new UserAuthorityResult())
				.getResult(new ResultParameter(isSuccess)
				.withMessage("권한이 없는 사용자 입니다."));
	}
}
