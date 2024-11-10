package com.sns.ajaxresult;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResultParameter<T> {

    private ResultStatusCode code;
    private String message;
    private boolean success;
    private T data;
    
    public ResultParameter(ResultStatusCode code) {
        this.code = code;
    }
    
    public ResultParameter<T> withBoolean (boolean success) {
        this.success = success;
        return this;
    }


	public ResultParameter<T> withMessage(String message) {
        this.message = message;
		return this;
    }
    
    public ResultParameter<T> withData (T data) {
    	this.data = data;
    	return this;
    }

	public ResultStatusCode getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}   
	
	public boolean isSuccess() {
		return success;
	}
}
