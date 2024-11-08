package com.sns.ajaxresult;

import java.util.HashMap;
import java.util.Map;

public class UserAuthorityResult implements AjaxResult {

	@Override
	public Map<String, Object> getResult(ResultParameter param) {
		Map<String, Object> result = new HashMap<>();		
		if (param.getMessage() == null) {
			result.put("code", 403);
			result.put("error_message", "로그인이 필요한 서비스 입니다.");
		} else if (param.isSuccess()) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", param.getMessage());
		}
		return result;
	}


}
