package com.sns.timeline;

import java.util.OptionalInt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.user.dto.UserSimple;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TimelineController {
	private final TimelineBO timelineBO;
	
	@GetMapping("/timeline")
	public String timeline(Model model,
			HttpSession session) {
		UserSimple user = (UserSimple)session.getAttribute("userSimple");
		OptionalInt userId = OptionalInt.empty();
		if (user != null) {
			userId = OptionalInt.of(user.getUserId()); 
		}
		model.addAttribute("postCardList", timelineBO.generatePostCardList(userId));
		return "timeline/timeline";
	}
}
