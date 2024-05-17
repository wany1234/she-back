package com.she.security.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.she.config.JwtSettings;
import com.she.security.auth.JwtJoseAuthenticationToken;
import com.she.security.auth.jwt.extractor.TokenExtractor;
import com.she.security.model.token.RawAccessJoseJwtToken;

/**
 * Performs validation of provided JWT Token. /api/auth/refresh,
 * /api/auth/login을 제외하고 /api/* 모든 요청에 적용됨. 요청정보중 X-Authorization 헤더에서 액세스 토큰을
 * 확인을 하고, 토큰을 포함하고 있다면 JwtAuthenticationProvider에게 권한을 위임하고 그렇지 않은 경우
 * authorization exception을 발생시킨다.
 *
 * @author vladimir.stankovic JOSE LIBRARY 사용 Aug 5, 2016
 */

public class JwtJoseTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtJoseTokenAuthenticationProcessingFilter.class);

    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;
    private final JwtSettings jwtSettings;

    @Autowired
    public JwtJoseTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, TokenExtractor tokenExtractor, JwtSettings jwtSettings, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        // ACCESS_TOKEN정보 조회
        String tokenPayload = request.getHeader(jwtSettings.getTokenHeader());

        // 토큰의 유효성 검증
        RawAccessJoseJwtToken token = new RawAccessJoseJwtToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtJoseAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
