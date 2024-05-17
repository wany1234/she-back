package com.she.security.model.token;

import java.text.ParseException;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.authentication.BadCredentialsException;*/
import org.springframework.security.authentication.BadCredentialsException;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.JwtExpiredTokenException;
/*import com.she.security.exception.JwtExpiredTokenException;*/

public class RawAccessJoseJwtToken implements JwtToken {
    private static Logger logger = LoggerFactory.getLogger(RawAccessJoseJwtToken.class);

    private String token;

    public RawAccessJoseJwtToken(String token) {
        this.token = token;
    }

    /**
     * Parses and validates JWT Token signature.
     * 
     * @throws ValidationException
     *
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     *
     */
    public JWTClaimsSet parseClaims(String signingKey) throws ValidationException {
        try {
            byte[] secretKey = signingKey.getBytes();
            // Configuring a JWT Processor
            ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
            JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(secretKey);
            JWEKeySelector<SimpleSecurityContext> jweKeySelector = new JWEDecryptionKeySelector<SimpleSecurityContext>(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
            jwtProcessor.setJWEKeySelector(jweKeySelector);
            return jwtProcessor.process(this.token, null);
        } catch (BadJWTException e) {
            logger.error("BadJWTException Token : " + e.getMessage(), e);
            if (e.getMessage().toLowerCase().contains("expired".toLowerCase())) {
                logger.error("#D#E#B#U#G# parseClaims  JWT Token is expired");
                // JWT Token expired
                throw new ValidationException("M0000000033", ExceptionCode.ACCESS_EXPIRED.errorCode());
            } else {
                logger.error("#D#E#B#U#G# parseClaims  Invalid JWT Token");
                // Invalid JWT token
                throw new ValidationException("M0000000034", ExceptionCode.INVALID.errorCode());
            }

        } catch (ParseException | BadJOSEException | JOSEException e) {
            logger.error("Invalid JWT Token", e);
            logger.error("#D#E#B#U#G# parseClaims  Invalid JWT Token");
            // Invalid JWT token
            throw new ValidationException("M0000000034", ExceptionCode.INVALID.errorCode());
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
