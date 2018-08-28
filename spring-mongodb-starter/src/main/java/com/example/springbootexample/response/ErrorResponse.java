package com.example.springbootexample.response;

public class ErrorResponse extends BaseResponse{

    public ErrorResponse(){

    }

    public ErrorResponse(String message){
        super.setMessage(message);
        super.setSuccess(false);
    }
}
