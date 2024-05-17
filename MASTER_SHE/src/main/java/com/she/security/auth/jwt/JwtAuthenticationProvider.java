package com.she.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.she.config.JwtSettings;
import com.she.security.auth.JwtAuthenticationToken;
import com.she.security.auth.UserContext;
import com.she.security.exception.JwtExpiredTokenException;
import com.she.security.model.token.RawAccessJwtToken;
import com.she.security.util.UserContextName;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication.
 *
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    private final JwtSettings jwtSettings;

    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        try {
            Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());

            List<String> scopes = jwsClaims.getBody().get(UserContextName.scopes.name(), List.class);
            List<GrantedAuthority> authorities = scopes.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority))
                    .collect(Collectors.toList());

            UserContext context = UserContext.create(
                    /*(String) jwsClaims.getBody().get(UserContextName.subdomain.name())
                    , Long.parseLong(jwsClaims.getBody().get(UserContextName.userpk.name()).toString())*/
                    jwsClaims.getBody().getSubject(),
                    (String) jwsClaims.getBody().get(UserContextName.dispname.name()),
                    (String) jwsClaims.getBody().get(UserContextName.clientip.name()),
                    (String) jwsClaims.getBody().get(UserContextName.expiredtime.name()),
                    rawAccessToken.getToken(),
                    rawAccessToken.getToken(),
                    authorities);
            return new JwtAuthenticationToken(context, context.getAuthorities());
        } catch (JwtExpiredTokenException ex) {
            logger.debug("AUTHAPP #D#E#B#U#B# JwtExpiredTokenException");
            throw new JwtExpiredTokenException("JWT Token expired");
        } catch (UnsupportedJwtException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (MalformedJwtException e) {
            throw new BadCredentialsException("Invalid JWT token: ", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid JWT token: ", e);
        } catch (ValidationException e) {
            logger.debug("AUTHAPP #D#E#B#U#B# JwtExpiredTokenException");
            throw new JwtExpiredTokenException("JWT Token expired");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
