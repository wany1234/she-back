package com.she.security.model.token;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.security.authentication.BadCredentialsException;

import com.she.security.exception.JwtExpiredTokenException;

/*import org.springframework.security.authentication.BadCredentialsException;*/
/*import com.she.security.exception.JwtExpiredTokenException;*/

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * RefreshToken
 *
 * @author vladimir.stankovic
 *
 *         Aug 19, 2016
 */
@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {

    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    /**
     * Creates and validates Refresh token
     *
     * @param token
     * @param signingKey
     *
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     *
     * @return
     * @throws ValidationException
     */
    public Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) throws ValidationException {
        Jws<Claims> claims = token.parseClaims(signingKey);

        List<String> scopes = claims.getBody().get("scopes", List.class);

        if (scopes == null || scopes.isEmpty()
        /*
         * || !scopes.stream().filter(scope ->
         * Scopes.REFRESH_TOKEN.authority().equals(scope)).findFirst().isPresent
         * ()
         */) {
            // 2017.08.03 위 주석을 제거하면 REFRESH_TOKEN Authority가 없는 경우 토큰을 업데이트 할 수
            // 없다... 이 부분이 왜 이렇게 구성되었는지 확인해 볼 것.
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }
}
