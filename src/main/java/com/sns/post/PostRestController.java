package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
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
		Map<String, Object> result = new HashMap<>();
		if (user == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 하세요.");
			return result;
		}
		PostEntity post = postBO.addPost(user.getUserId(), content, file, user.getUserLoginId() );
		if (post != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "게시글 업로드에 실패했습니다.");
		}
		return result;
	}
}
