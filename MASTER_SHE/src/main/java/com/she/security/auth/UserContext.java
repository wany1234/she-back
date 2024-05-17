package com.she.security.auth;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

/**
 *
 * @author vladimir.stankovic
 *
 *         Aug 4, 2016
 *
 *         subdomain 추가 : 테넌트 처리를 위한.
 */
@Data
public class UserContext {
    private final String username;
    private final List<GrantedAuthority> authorities;
    private final String dispname;
    private final String expiredtime;
    private final String accessToken;
    private final String refreshToken;
    // 클라이언트아이피
    private final String clientip;

    private UserContext(String username, String dispname, String clientip, String expiredtime, String accessToken, String refreshToken, /*
                                                                                                                                         * ,
                                                                                                                                         * String
                                                                                                                                         * tenantName
                                                                                                                                         */
            List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
        this.dispname = dispname;
        this.clientip = clientip;
        this.expiredtime = expiredtime;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static UserContext create(String username, String dispname, String clientip, String expiredtime, String accessToken, String refreshToken, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) {
            // 사용자 id가 없습니다.
            throw new IllegalArgumentException("M0000000021");
        }
        if (StringUtils.isBlank(dispname)) {
            // 사용자 이름이 없습니다.
            throw new IllegalArgumentException("M0000000022");
        }
        return new UserContext(username, dispname, clientip, expiredtime, accessToken, refreshToken, authorities);
    }

    // public String getUsername() {
    // return username;
    // }
    //
    // public List<GrantedAuthority> getAuthorities() {
    // return authorities;
    // }
    //
    // public String getDispname() {
    // return dispname;
    // }
    //
    // public String getAccessToken() {
    // return accessToken;
    // }
    //
    // public String getRefreshToken() {
    // return refreshToken;
    // }
    //
    // public String getClientip() {
    // return clientip;
    // }
    //
    // public String getExpiredtime() {
    // return expiredtime;
    // }

}
