package com.example.springbootexample.security;

import com.example.springbootexample.response.ErrorResponse;
import com.example.springbootexample.util.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        ErrorResponse lcErrorResponse = new ErrorResponse();
        lcErrorResponse.setMessage("Unauthorised access");
        lcErrorResponse.setSuccess(false);
        JSONUtil.generateJSONResponse(httpServletResponse, lcErrorResponse);
    }
}