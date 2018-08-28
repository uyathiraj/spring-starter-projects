package com.example.springbootexample.security;

public interface AppSecurityConstants {
       String HEADER_STRING = "Authorization";
     long EXPIRATION_TIME=60*60*60*24;
     String SECRET = "AppSecret";
     String SIGN_UP_URL = "/login";
    String AUTHORITIES_KEY = "scopes";

}
