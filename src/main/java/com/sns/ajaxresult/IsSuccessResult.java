package com.sns.ajaxresult;

import java.util.HashMap;
import java.util.Map;

public class IsSuccessResult implements AjaxResult {

	@Override
	public Map<String, Object> getResult(ResultParameter<?> param) {
		Map<String, Object> result = new HashMap<>();
		if (param.isSuccess()) {
			result.put("code", ResultStatusCode.OK.code());
			result.put("result", "성공");
		} else {
			result.put("code", param.getCode().code());
			result.put("error_message", param.getMessage());
		}

		return result;
	}
}
