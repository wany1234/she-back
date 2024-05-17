package com.she.security.model.token;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jwt.JWTClaimsSet;
import com.she.config.JwtSettings;
import com.she.security.auth.UserContext;
import com.she.security.model.Scopes;
import com.she.security.util.UserContextName;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 *
 * @author vladimir.stankovic
 *
 *         May 31, 2016
 */
@Component
public class JwtTokenFactory {

    /*
     * private static final Logger LOGGER =
     * LoggerFactory.getLogger(JwtTokenFactory.class);
     */
    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    /**
     * create access token using Nimbus jose library
     * 
     * @param userContext
     * @return
     */
    public AccessJoseJwtToken createAccessNimbusJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            try {
                // Cannot create JWT Token without username
                throw new IllegalArgumentException("M0000000026");
            } catch (Exception e) {
                throw e;
            }
        }

        if (StringUtils.isBlank(userContext.getDispname())) {
            try {
                // Cannot create JWT Token without dispname
                throw new IllegalArgumentException("M0000000027");
            } catch (Exception e) {
                throw e;
            }
        }

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) {
            try {
                // User doesn't have any privileges
                throw new IllegalArgumentException("M0000000028");
            } catch (Exception e) {
                throw e;
            }
        }

        LocalDateTime currentTime = LocalDateTime.now();

        JWTClaimsSet claims = new JWTClaimsSet.Builder().subject(userContext.getUsername()).claim(UserContextName.scopes.name(), userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList())).claim(UserContextName.dispname.name(), userContext.getDispname())
                .claim(UserContextName.clientip.name(), userContext.getClientip()).claim(UserContextName.expiredtime.name(), userContext.getExpiredtime()).claim(UserContextName.userid.name(), userContext.getUsername()).issuer(settings.getTokenIssuer()).issueTime(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .expirationTime(Date.from(currentTime.plusMinutes(settings.getTokenExpirationTime()).atZone(ZoneId.systemDefault()).toInstant())).build();

        Payload payload = new Payload(claims.toJSONObject());

        JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);

        // Creating the encrypter
        String secret = settings.getTokenSigningKey();
        byte[] secretKey = secret.getBytes();
        String token = "";

        try {
            DirectEncrypter encrypter = new DirectEncrypter(secretKey);
            JWEObject jweObject = new JWEObject(header, payload);
            jweObject.encrypt(encrypter);
            token = jweObject.serialize();
        } catch (JOSEException e) {
            try {
                // Fail to make token.
                throw new IllegalArgumentException("M0000000029");
            } catch (Exception ex) {
                throw ex;
            }
        }

        return new AccessJoseJwtToken(token, claims);
    }

    /**
     * create refresh token using Nimbus jose library
     * 
     * @param userContext
     * @return
     */
    public AccessJoseJwtToken createRefreshNimbusToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            try {
                // Cannot create JWT Token without username
                throw new IllegalArgumentException("M0000000026");
            } catch (Exception e) {
                throw e;
            }
        }

        if (StringUtils.isBlank(userContext.getDispname())) {
            try {
                // Cannot create JWT Token without dispname
                throw new IllegalArgumentException("M0000000027");
            } catch (Exception e) {
                throw e;
            }
        }

        LocalDateTime currentTime = LocalDateTime.now();

        JWTClaimsSet claims = new JWTClaimsSet.Builder().subject(userContext.getUsername()).claim(UserContextName.scopes.name(), Arrays.asList(Scopes.REFRESH_TOKEN.authority())).claim(UserContextName.dispname.name(), userContext.getDispname()).claim(UserContextName.clientip.name(), userContext.getClientip())
                .claim(UserContextName.expiredtime.name(), userContext.getExpiredtime()).claim(UserContextName.userid.name(), userContext.getUsername()).issuer(settings.getTokenIssuer()).jwtID(UUID.randomUUID().toString()).issueTime(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .expirationTime(Date.from(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).atZone(ZoneId.systemDefault()).toInstant()))
                /*
                 * .issueTime(new Date(new Date().getTime()))
                 * .expirationTime(new Date(new Date().getTime() +
                 * settings.getRefreshTokenExpTime()*1000*60))
                 */
                .build();

        Payload payload = new Payload(claims.toJSONObject());

        // Creating the header
        JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);

        // Creating the encrypter
        String secret = settings.getTokenSigningKey();
        byte[] secretKey = secret.getBytes();
        String token = "";

        try {
            DirectEncrypter encrypter = new DirectEncrypter(secretKey);
            JWEObject jweObject = new JWEObject(header, payload);
            jweObject.encrypt(encrypter);
            token = jweObject.serialize();
        } catch (JOSEException e) {
            // Fail to make token.
            throw new IllegalArgumentException("M0000000029");
        }

        return new AccessJoseJwtToken(token, claims);
    }

    /**
     * Factory method for issuing new JWT Tokens. (Using JJWT Library)
     *
     * @param username
     * @param roles
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            try {
                // Cannot create JWT Token without username
                throw new IllegalArgumentException("M0000000026");
            } catch (Exception e) {
                throw e;
            }
        }

        if (StringUtils.isBlank(userContext.getDispname())) {
            try {
                // Cannot create JWT Token without dispname
                throw new IllegalArgumentException("M0000000027");
            } catch (Exception e) {
                throw e;
            }

        }

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) {
            try {
                // User doesn't have any privileges
                throw new IllegalArgumentException("M0000000028");
            } catch (Exception e) {
                throw e;
            }
        }

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put(UserContextName.scopes.name(), userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        claims.put(UserContextName.userid.name(), userContext.getUsername());
        claims.put(UserContextName.dispname.name(), userContext.getDispname());
        claims.put(UserContextName.clientip.name(), userContext.getClientip());
        claims.put(UserContextName.expiredtime.name(), userContext.getExpiredtime());

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder().setClaims(claims).setIssuer(settings.getTokenIssuer()).setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant())).setExpiration(Date.from(currentTime.plusMinutes(settings.getTokenExpirationTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey()).compact();

        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            try {
                // Cannot create JWT Token without username
                throw new IllegalArgumentException("M0000000026");
            } catch (Exception e) {
                throw e;
            }

        }

        if (StringUtils.isBlank(userContext.getDispname())) {
            try {
                // Cannot create JWT Token without dispname
                throw new IllegalArgumentException("M0000000027");
            } catch (Exception e) {
                throw e;
            }
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put(UserContextName.scopes.name(), Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        claims.put(UserContextName.userid.name(), userContext.getUsername());
        claims.put(UserContextName.dispname.name(), userContext.getDispname());
        claims.put(UserContextName.clientip.name(), userContext.getClientip());
        claims.put(UserContextName.userid.name(), userContext.getUsername());

        String token = Jwts.builder().setClaims(claims).setIssuer(settings.getTokenIssuer()).setId(UUID.randomUUID().toString()).setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant())).setExpiration(Date.from(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey()).compact();

        return new AccessJwtToken(token, claims);
    }
}
