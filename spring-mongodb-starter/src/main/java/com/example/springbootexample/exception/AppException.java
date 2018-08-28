package com.example.springbootexample.exception;

import com.example.springbootexample.response.ErrorResponse;

public class AppException extends Exception{
    private ErrorResponse errorResponse;
    public AppException(ErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
