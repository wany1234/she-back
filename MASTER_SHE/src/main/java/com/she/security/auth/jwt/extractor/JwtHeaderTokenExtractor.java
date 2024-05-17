package com.she.security.auth.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

/**
 * An implementation of {@link TokenExtractor} extracts token from
 * Authorization: Bearer scheme.
 *
 * @author vladimir.stankovic
 *
 *         Aug 5, 2016
 */
@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
    public static String HEADER_PREFIX = "Bearer ";

    private static final Logger logger = LoggerFactory.getLogger(JwtHeaderTokenExtractor.class);

    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
            try {
                throw new AuthenticationServiceException("Authorization header cannot be blank!");
            } catch (NullPointerException ne) {
                logger.error(ne.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        if (header.length() < HEADER_PREFIX.length()) {
            try {
                throw new AuthenticationServiceException("Invalid authorization header size.");
            } catch (NullPointerException ne) {
                logger.error(ne.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
