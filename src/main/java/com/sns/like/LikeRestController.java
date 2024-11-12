package com.sns.like;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.ajaxresult.AjaxResultSimpleFactory;
import com.sns.ajaxresult.ResultParameter;
import com.sns.ajaxresult.ResultStatusCode;
import com.sns.like.bo.LikeBO;
import com.sns.user.dto.UserSimple;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeRestController {
	private final LikeBO likeBO;
	
	// GET: /like/3 /like/{postId} @PathVariable(name="postId")
	
	@GetMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable(name="postId") int postId,
			HttpSession session) {
		
		UserSimple user = (UserSimple)session.getAttribute("userSimple");
		if (user == null) {
			return new AjaxResultSimpleFactory().isSuccess()
					 .getResult(new ResultParameter<>(ResultStatusCode.FORBIDDEN)
							 .withMessage("로그인이 필요한 서비스입니다."));
		}
				

		return new AjaxResultSimpleFactory().isSuccess()
				.getResult(new ResultParameter<>(ResultStatusCode.FAIL)
							.withMessage("좋아요 조회에 실패했습니다.")
							.withBoolean(likeBO.toggleLike(postId, user.getUserId())));
	}
}
