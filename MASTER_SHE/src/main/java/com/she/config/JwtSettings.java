package com.she.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*import com.she.security.model.token.JwtToken;*/

@Component
@ConfigurationProperties(prefix = "swing.security.jwt")
public class JwtSettings {

    /**
     * {@link JwtToken} will expire after this time.
     */
    private Integer tokenExpirationTime;

    /**
     * {@link JwtToken} can be refreshed during this timeframe.
     */
    private Integer refreshTokenExpTime;

    /**
     * Token issuer.
     */
    private String tokenIssuer;

    /**
     * Key is used to sign {@link JwtToken}.
     */
    private String tokenSigningKey;

    /**
     * token header key
     */
    private String tokenHeader;

    /**
     * tenant header key
     */
    private String tenantHeader;

    /**
     * 요청한 클라이언트의 아이피주소를 클라이언트에서 직접 보내주는경우 헤더 키값
     */
    private String localIp;

    /**
     * 아이피로 중복 로그인을 비교하는 경우 OPEN토큰 또는 10년토큰의 아이피를 PASS하기 위해서 사용함.
     */
    private String passip;
    private String passip2;

    /**
     * 개발 및 장기토큰
     */
    private String devtoken;

    /**
     * OPEN서비스 요청시 하용하는 토큰, 10년 장기토큰
     */
    private String opentoken;

    /**
     * Swagger에서 데이터를 처리하는 경우 키
     */
    private String swaggerHeader;
    /**
     * Swagger에서 데이터를 처리하는 경우 고유값
     */
    private String swaggerkey;

    private String validmode;

    public Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTenantHeader() {
        return tenantHeader;
    }

    public void setTenantHeader(String tenantHeader) {
        this.tenantHeader = tenantHeader;
    }

    public String getLocalIp() {
        return localIp;
    }

    public String getPassip() {
        return passip;
    }

    public String getDevtoken() {
        return devtoken;
    }

    public String getOpentoken() {
        return opentoken;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public void setPassip(String passip) {
        this.passip = passip;
    }

    public void setDevtoken(String devtoken) {
        this.devtoken = devtoken;
    }

    public void setOpentoken(String opentoken) {
        this.opentoken = opentoken;
    }

    public String getPassip2() {
        return passip2;
    }

    public void setPassip2(String passip2) {
        this.passip2 = passip2;
    }

    public String getSwaggerHeader() {
        return swaggerHeader;
    }

    public String getSwaggerkey() {
        return swaggerkey;
    }

    public void setSwaggerHeader(String swaggerHeader) {
        this.swaggerHeader = swaggerHeader;
    }

    public void setSwaggerkey(String swaggerkey) {
        this.swaggerkey = swaggerkey;
    }

    public String getValidmode() {
        return validmode;
    }

    public void setValidmode(String validmode) {
        this.validmode = validmode;
    }

}
