package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.ajaxresult.AjaxResultSimpleFactory;
import com.sns.ajaxresult.ResultParameter;
import com.sns.ajaxresult.ResultStatusCode;
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
			return new AjaxResultSimpleFactory().isSuccess()
					 .getResult(new ResultParameter<>(ResultStatusCode.FORBIDDEN)
							 .withMessage("로그인이 필요한 서비스입니다."));
		}
		boolean isSuccess = commentBO.addComment(user.getUserId(), postId, content);
		
		return new AjaxResultSimpleFactory().isSuccess()
				.getResult(new ResultParameter<>(ResultStatusCode.FAIL)
							.withMessage("댓글 등록에 실패했습니다.")
							.withBoolean(isSuccess));
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> commentDelete(
			@RequestParam("commentId") int commentId,
			HttpSession session) {
		
		UserSimple user = (UserSimple)session.getAttribute("userSimple");
		if (user == null) {
			return new AjaxResultSimpleFactory().isSuccess()
					 .getResult(new ResultParameter<>(ResultStatusCode.FORBIDDEN)
							 .withMessage("로그인이 필요한 서비스입니다."));
		}
		
		boolean isSuccess = commentBO.deleteCommentById(commentId);
		return new AjaxResultSimpleFactory().isSuccess()
				.getResult(new ResultParameter<>(ResultStatusCode.FAIL)
							.withMessage("댓글 삭제에 실패했습니다.")
							.withBoolean(isSuccess));
	}

}
