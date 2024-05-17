package com.she.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
            throws IOException, ServletException {


        // 오류를 REST방식으로 내려준다.
        // response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        response.getWriter().append("Not authenticated");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
