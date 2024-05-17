package com.she.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWTClaimsSet;
import com.she.config.JwtSettings;
import com.she.security.auth.JwtAuthenticationToken;
import com.she.security.auth.JwtJoseAuthenticationToken;
import com.she.security.auth.UserContext;
import com.she.security.auth.jwt.extractor.TokenExtractor;
import com.she.security.exception.ExceptionCode;
import com.she.security.model.token.RawAccessJoseJwtToken;
import com.she.security.util.UserContextName;

/**
 * 인증서버에 토큰을 전달하여 인증을 확인한다.
 */

@PropertySource(value = {"classpath:application.properties"})
@Component
public class JwtJoseAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtJoseAuthenticationProvider.class);

    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtSettings jwtSettings;


    /**
     * 토큰의 유효성 검증은 이미 API Gateway에서 끝났기 때문에, 토큰을 풀어서 사용하기만 하면 된다.
     * 단, api/auth/login, api/auth/refresh, api/auth/valid 는 예외
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("#D#E#B#U#G# authenticate");
        RawAccessJoseJwtToken rawAccessToken = (RawAccessJoseJwtToken) authentication.getCredentials();
        UserContext context = this.validJwtToken(rawAccessToken.getToken());
        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    private UserContext validJwtToken(String tokenPayload) {
        RawAccessJoseJwtToken rawToken = new RawAccessJoseJwtToken(tokenPayload);
        JWTClaimsSet jwsClaims;
        try {
            jwsClaims = rawToken.parseClaims(jwtSettings.getTokenSigningKey());
            @SuppressWarnings("unchecked")
            List<String> scopes = (List<String>) jwsClaims.getClaim(UserContextName.scopes.name());
            List<GrantedAuthority> authorities = scopes.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority))
                    .collect(Collectors.toList());

            return UserContext.create(
                    /*(String) jwsClaims.getClaim(UserContextName.scopes.name()),
                    Long.parseLong(jwsClaims.getClaim(UserContextName.userpk.name()).toString()),*/
                    jwsClaims.getSubject(),
                    (String) jwsClaims.getClaim(UserContextName.dispname.name()),
                    (String) jwsClaims.getClaim(UserContextName.clientip.name()),
                    (String) jwsClaims.getClaim(UserContextName.expiredtime.name()),
                    rawToken.getToken(),
                    rawToken.getToken(),
                    authorities);
        } catch (ValidationException e) {
            if (ExceptionCode.INVALID.errorCode().equalsIgnoreCase(e.getErrorCode())) {
                throw new BadCredentialsException("사용자 인증에 실패했습니다. 잘못된 토큰입니다.");
            } else if (ExceptionCode.REFRESH_EXPIRED.errorCode().equalsIgnoreCase(e.getErrorCode())) {
                // "인증이 실패했습니다. 토큰이 만료되었습니다."
                throw new CredentialsExpiredException(ExceptionCode.REFRESH_EXPIRED.errorCode());
            } else if (ExceptionCode.ACCESS_EXPIRED.errorCode().equalsIgnoreCase(e.getErrorCode())) {
                // "인증이 실패했습니다. 토큰이 만료되었습니다."
                throw new CredentialsExpiredException(ExceptionCode.ACCESS_EXPIRED.errorCode());
            } else {
                throw new BadCredentialsException("사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.");
            }
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtJoseAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
