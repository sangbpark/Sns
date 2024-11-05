package com.sns.timeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;

@Controller
public class TimelineController {
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline")
	public String timeline(Model model) {
		model.addAttribute("timelineList", timelineBO.getTimelineList());
		return "timeline/timeline";
	}
}
