package com.she.security.auth.ajax;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.she.manage.model.User;
import com.she.manage.service.UserService;
import com.she.security.auth.UserContext;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.RestAuthException;

/**
 *
 * @author vladimir.stankovic
 *
 *         Aug 3, 2016
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationProvider.class);

	private final BCryptPasswordEncoder encoder;
    // private final DatabaseUserService userService;
    private final UserService userService;

    @Autowired
    public AjaxAuthenticationProvider(final UserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String pwd = (String) authentication.getCredentials();

        User user = null;
        try {
            try {
                user = userService.getUser(username);
            } catch (NullPointerException e) {
            	logger.error(e.getMessage());
            } catch (Exception e) {
            	logger.error(e.getMessage());
            	throw new RestAuthException(HttpStatus.UNAUTHORIZED, "Authentication Failed. User not found: " + username, ExceptionCode.UNAUTHORIZED.name());
            }
        } catch (RestAuthException e) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (!encoder.matches(pwd, user.getUserPwdSHA())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        // if (user.getUserRoles() == null) throw new
        // InsufficientAuthenticationException("User has no roles assigned");

        // user.getUserRolesList()
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        UserContext userContext = UserContext.create(user.getUserId(), user.getUserNm(), "", "", "", "", authorities);

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
