package com.example.springbootexample.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springbootexample.response.ErrorResponse;
import com.example.springbootexample.util.JSONUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.springbootexample.security.AppSecurityConstants.AUTHORITIES_KEY;

@Component
public class AppAuthorizationFilter extends BasicAuthenticationFilter {

    public AppAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AppSecurityConstants.HEADER_STRING);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(request);
        } catch (TokenExpiredException e) {
            setErrorResponse(response, "Token expired");
            return;
        } catch (JWTDecodeException e) {
            setErrorResponse(response, "Invalid Token");
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private void setErrorResponse(HttpServletResponse response, String message) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setSuccess(false);
        JSONUtil.generateJSONResponse(response, errorResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws TokenExpiredException, JWTDecodeException {
        String token = request.getHeader(AppSecurityConstants.HEADER_STRING);

        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(AppSecurityConstants.SECRET.getBytes()))
                .build();
        verifier.verify(token);
        DecodedJWT decoded = JWT.decode(token);
        String roles = decoded.getClaim(AUTHORITIES_KEY).asString();
        List<GrantedAuthority> roleList = new ArrayList<>();
        Arrays.stream(roles.split(",")).forEach(role->roleList.add(new SimpleGrantedAuthority(role)));
        String user = decoded.getSubject();
        return new UsernamePasswordAuthenticationToken(user, null,roleList);
    }
}


