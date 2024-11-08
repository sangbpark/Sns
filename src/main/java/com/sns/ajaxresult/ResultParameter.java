package com.sns.ajaxresult;

public class ResultParameter {

    private boolean success;
    private String message;
    

    public ResultParameter(boolean success) {
        this.success = success;
    }

    public ResultParameter withMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
