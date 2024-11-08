package com.sns.ajaxresult;

import java.util.Map;

public class AjaxResultBO  {
	private AjaxResult ajaxResult;
	
	public AjaxResultBO(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	
	public Map<String, Object> getResult(ResultParameter param) {
		return ajaxResult.getResult(param);
	}
}
