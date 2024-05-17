package com.she.security.auth.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model intended to be used for AJAX based authentication.
 *
 * @author vladimir.stankovic
 *
 *         Aug 3, 2016
 */

public class LoginRequest {
    private String username;
    private String userpwd;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String pwd) {
        this.username = username;
        this.userpwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return userpwd;
    }
}
