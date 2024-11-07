package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TimelineController {
	private final TimelineBO timelineBO;
	
	@GetMapping("/timeline")
	public String timeline(Model model) {
		model.addAttribute("timelineList", timelineBO.getTimelineList());
		return "timeline/timeline";
	}
}
