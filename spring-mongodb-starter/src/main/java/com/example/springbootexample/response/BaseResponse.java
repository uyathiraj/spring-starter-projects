package com.example.springbootexample.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private String message;
    private boolean success;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
