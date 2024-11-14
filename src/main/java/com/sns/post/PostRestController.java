package com.sns.post;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.ajaxresult.AjaxResultSimpleFactory;
import com.sns.ajaxresult.ResultParameter;
import com.sns.ajaxresult.ResultStatusCode;
import com.sns.post.bo.PostBO;
import com.sns.user.dto.UserSimple;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostRestController {
	private final PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam(value="content", required=false) String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		UserSimple user = (UserSimple)session.getAttribute("userSimple");
		if (user == null) {
			return new AjaxResultSimpleFactory()
					.isSuccess()
					.getResult(new ResultParameter<>(ResultStatusCode.FORBIDDEN)
							.withMessage("로그인이 필요한 서비스입니다."));
		}
		boolean isSuccess = postBO.addPost(user.getUserId(), content, file, user.getUserLoginId() );

		return new AjaxResultSimpleFactory()
				.isSuccess()
				.getResult(new ResultParameter<>(ResultStatusCode.FAIL)
							.withBoolean(isSuccess)
							.withMessage("게시글 등록에 실패했습니다."));
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> postDelete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		UserSimple user = (UserSimple)session.getAttribute("userSimple");
		if (user == null) {
			return new AjaxResultSimpleFactory()
					.isSuccess()
					.getResult(new ResultParameter<>(ResultStatusCode.FORBIDDEN)
							.withMessage("로그인이 필요한 서비스입니다."));
		}
		
		boolean isSuccess = postBO.deletePostByPostIdUserId(postId, user.getUserId());
		
		return new AjaxResultSimpleFactory()
				.isSuccess()
				.getResult(new ResultParameter<>(ResultStatusCode.FAIL)
							.withBoolean(isSuccess)
							.withMessage("게시글 삭제에 실패했습니다."));
	}
}
