package com.example.springbootexample.exception;

import com.example.springbootexample.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException appException){
        ErrorResponse errorResponse = appException.getErrorResponse();
        return new ResponseEntity(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex){
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse("System error occured");
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException ex){
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse("You are not authorized to access");
        return new ResponseEntity(errorResponse,HttpStatus.UNAUTHORIZED);
    }
}
