package com.she.security.auth;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.she.security.model.token.RawAccessJoseJwtToken;


/**
 * An {@link org.springframework.security.core.Authentication} implementation
 * that is designed for simple presentation of JwtToken.
 *
 * @author vladimir.stankovic
 *
 *         May 23, 2016
 */
public class JwtJoseAuthenticationToken extends AbstractAuthenticationToken {
   private static Logger logger = LoggerFactory.getLogger(JwtJoseAuthenticationToken.class);

	private static final long serialVersionUID = 2877954820905567501L;

    private RawAccessJoseJwtToken rawAccessToken;
    private UserContext userContext;

    public JwtJoseAuthenticationToken(RawAccessJoseJwtToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public JwtJoseAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        try {
            if (authenticated) {
                throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
            }
        } catch(NullPointerException e) {
        	logger.error(e.getMessage());
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	throw e;
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
}
