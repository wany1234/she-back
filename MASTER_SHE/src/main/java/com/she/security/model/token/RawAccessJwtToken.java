package com.she.security.model.token;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.authentication.BadCredentialsException;*/

import com.she.security.exception.ExceptionCode;
/*import com.she.security.exception.JwtExpiredTokenException;*/

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class RawAccessJwtToken implements JwtToken {
    private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    /**
     * Parses and validates JWT Token signature.
     * @throws ValidationException
     *
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     *
     */
    public Jws<Claims> parseClaims(String signingKey) throws ValidationException {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {

            logger.error("Invalid JWT Token", ex);
            logger.error("#D#E#B#U#G# parseClaims  Invalid JWT Token");
            throw new ValidationException("Invalid JWT token", ExceptionCode.INVALID.errorCode());
        } catch (ExpiredJwtException expiredEx) {
            logger.error("JWT Token is expired", expiredEx);
            logger.error("#D#E#B#U#G# parseClaims  JWT Token is expired");
            throw new ValidationException("JWT Token expired", ExceptionCode.ACCESS_EXPIRED.errorCode());
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
