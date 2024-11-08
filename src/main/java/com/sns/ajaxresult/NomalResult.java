package com.sns.ajaxresult;

import java.util.HashMap;
import java.util.Map;

public class NomalResult implements AjaxResult {

	@Override
	public Map<String, Object> getResult(ResultParameter param) {
		Map<String, Object> result = new HashMap<>();
		if (param.getMessage() == null) {
			result.put("code", 200);
			result.put("is_duplicate", param.isSuccess());
		} else if (param.isSuccess()) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", param.getMessage());
		}
		return result;
	}



}
