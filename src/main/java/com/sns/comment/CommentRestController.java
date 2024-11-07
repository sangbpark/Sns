package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		Map<String, Object> result = new HashMap<>();
		if (user == null) {
			result.put("code", 403);
			result.put("error_message", "로그인이 필요한 서비스 입니다.");
			return result;
		}
		boolean isSuccess = commentBO.addComment(user.getUserId(), postId, content);
		
		if (isSuccess) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "댓글 등록에 실패했습니다.");
		}
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> commentDelete() {
		Map<String, Object> result = new HashMap<>();
		return result;
	}

}
