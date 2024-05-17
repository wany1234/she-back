package com.she.security.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nimbusds.jwt.JWTClaimsSet;

/**
 * Raw representation of JWT Token.
 *
 * @author vladimir.stankovic
 *
 *         May 31, 2016
 */
public final class AccessJoseJwtToken implements JwtToken {
    private final String rawToken;

    @JsonIgnore
    private JWTClaimsSet claims;

    protected AccessJoseJwtToken(final String token, JWTClaimsSet claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return this.rawToken;
    }

    public JWTClaimsSet getClaims() {
        return claims;
    }
}
