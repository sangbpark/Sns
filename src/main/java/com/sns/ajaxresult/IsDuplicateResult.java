package com.sns.ajaxresult;

import java.util.HashMap;
import java.util.Map;

public class IsDuplicateResult implements AjaxResult {

	@Override
	public Map<String, Object> getResult(ResultParameter<?> param) {
		Map<String, Object> result = new HashMap<>();			
		result.put("code", 200);
		result.put("is_duplicate", param.isSuccess());
	
		return result;
	}
}
