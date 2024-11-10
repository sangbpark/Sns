package com.sns.ajaxresult;

import java.util.Map;

public interface AjaxResult {

	public Map<String, Object> getResult(ResultParameter<?> param);

}
