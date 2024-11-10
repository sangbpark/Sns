package com.sns.ajaxresult;

public class AjaxResultSimpleFactory  {

	public AjaxResult isSuccess() {
		return new IsSuccessResult();
	}
	
	public AjaxResult isDuplicate() {
		return new IsDuplicateResult();
	}
}
