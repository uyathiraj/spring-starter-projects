package com.example.springbootexample.response;

public class SuccessResponse<T> extends BaseResponse{

    private T data;

    public SuccessResponse(T data){
        super();
        this.data = data;
        super.setSuccess(true);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
