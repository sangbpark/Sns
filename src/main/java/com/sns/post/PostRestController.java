package com.sns.post;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.ajaxresult.AjaxResultBO;
import com.sns.ajaxresult.NomalResult;
import com.sns.ajaxresult.ResultParameter;
import com.sns.ajaxresult.UserAuthorityResult;
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
			return new AjaxResultBO(new UserAuthorityResult())
					 .getResult(new ResultParameter(false));
		}
		boolean isSuccess = postBO.addPost(user.getUserId(), content, file, user.getUserLoginId() );

		return new AjaxResultBO(new NomalResult())
				.getResult(new ResultParameter(isSuccess)
				.withMessage("게시글 등록에 실패했습니다."));
	}
}
