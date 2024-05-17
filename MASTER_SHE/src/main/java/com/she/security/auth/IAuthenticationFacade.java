package com.she.security.auth;

import org.springframework.security.core.Authentication;

/**
 *
 * @클래스명 : IAuthenticationFacade.java
 * @설명 : AuthenticationFacade의 인터페이스
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
public interface IAuthenticationFacade {

    Authentication getAuthentication();

    String getUserName(String defaultUserName);

    String getDispName();

    String getAccessToken();

    String getRefreshToken();
}
