package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.ajaxresult.AjaxResultBO;
import com.sns.ajaxresult.NomalResult;
import com.sns.ajaxresult.ResultParameter;
import com.sns.ajaxresult.UserAuthorityResult;
import com.sns.comment.bo.CommentBO;
import com.sns.user.dto.UserSimple;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	private final CommentBO commentBO;
	
//	DI
//	private final CommentBO commentBO;
//	
//	public CommentRestController(CommentBO commentBO) {
//		this.commentBO = commentBO;
//	}
	
	@PostMapping("/create")
	public Map<String, Object> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		UserSimple user = (UserSimple)session.getAttribute("userSimple");
		if (user == null) {
			return new AjaxResultBO(new UserAuthorityResult())
					 .getResult(new ResultParameter(false));
		}
		boolean isSuccess = commentBO.addComment(user.getUserId(), postId, content);
		
		return new AjaxResultBO(new NomalResult())
				.getResult(new ResultParameter(isSuccess)
				.withMessage("댓글 등록에 실패했습니다."));
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> commentDelete() {
		Map<String, Object> result = new HashMap<>();
		return result;
	}

}
