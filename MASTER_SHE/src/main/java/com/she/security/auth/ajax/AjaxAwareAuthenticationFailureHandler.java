package com.she.security.auth.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.she.security.exception.AuthMethodNotSupportedException;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.JwtExpiredTokenException;
import com.she.security.model.ErrorCode;
import com.she.security.model.ErrorResponse;

/**
 *
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(AjaxAwareAuthenticationFailureHandler.class);

    private final ObjectMapper mapper;

    @Autowired
    public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        if (e instanceof BadCredentialsException) {
            logger.error("#D#E#B#U#G# " + "Invalid username or password");
            mapper.writeValue(response.getWriter(), new ErrorResponse("Invalid username or password", ErrorCode.AUTHENTICATION,
                    ExceptionCode.INVALID.errorCode(), HttpStatus.UNAUTHORIZED));
        } else if (e instanceof JwtExpiredTokenException) {
            logger.error("#D#E#B#U#G# " + "Token has expired");
            mapper.writeValue(response.getWriter(), new ErrorResponse("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED,
                    ExceptionCode.ACCESS_EXPIRED.errorCode(), HttpStatus.UNAUTHORIZED));
        } else if (e instanceof CredentialsExpiredException) {
            logger.error("#D#E#B#U#G# " + "Token has expired");
            mapper.writeValue(response.getWriter(), new ErrorResponse("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED,
                    e.getMessage(), HttpStatus.UNAUTHORIZED));
        } else if (e instanceof AuthMethodNotSupportedException) {
            logger.error("#D#E#B#U#G# " + "AuthMethodNotSupportedException");
            mapper.writeValue(response.getWriter(), new ErrorResponse(e.getMessage(), ErrorCode.AUTHENTICATION,
                    ExceptionCode.INVALID.errorCode(), HttpStatus.UNAUTHORIZED));
        } else if (e instanceof AuthenticationServiceException) {
            logger.error("#D#E#B#U#G# " + "AuthenticationServiceException");
            mapper.writeValue(response.getWriter(), new ErrorResponse(e.getMessage(), ErrorCode.AUTHENTICATION,
                    ExceptionCode.INVALID.errorCode(), HttpStatus.UNAUTHORIZED));
        } else {
            logger.error("#D#E#B#U#G# " + "Authentication failed");
            mapper.writeValue(response.getWriter(), new ErrorResponse("Authentication failed", ErrorCode.AUTHENTICATION,
                    ExceptionCode.INVALID.errorCode(), HttpStatus.UNAUTHORIZED));
        }
    }
}
