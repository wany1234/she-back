package com.she.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
*
 * @클래스명 : AuthenticationFacade.java
 * @설명 : 인증정보 저장정보
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
*/
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 사용자ID조회
     * <pre>
     * 개요 :
     * </pre>
     * @ClassName : AuthenticationFacade.java
     * @MethodName : getUserName
     * @param defaultUserName : 값이 없는 경우 보여줄 사용자명
     * @return
     */
    public String getUserName(String defaultUserName) {

        String userName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserContext) {
                userName = ((UserContext) principal).getUsername();
            }
        }

        if (userName.isEmpty()) {
            return defaultUserName;
        } else {
            return userName;
        }

    }

    /**
    *
    * <pre>
    * 개요 : 사용자명 조회
    * </pre>
    * @ClassName : AuthenticationFacade.java
    * @MethodName : getDispName
    * @return
    */
    public String getDispName() {
        String dispname = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserContext) {
                dispname = ((UserContext) principal).getDispname();
            }
        }

        return dispname;
    }

    /**
     *
     * <pre>
     * 개요 : AccessToken조회
     * </pre>
     * @ClassName : AuthenticationFacade.java
     * @MethodName : getAccessToken
     * @return
     */
    public String getAccessToken() {
        String accessToken = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserContext) {
                accessToken = ((UserContext) principal).getAccessToken();
            }
        }

        return accessToken;
    }

    /**
     *
     * <pre>
     * 개요 : RefreshToken 조회
     * </pre>
     * @ClassName : AuthenticationFacade.java
     * @MethodName : getRefreshToken
     * @return
     */
    public String getRefreshToken() {
        String refreshToken = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserContext) {
                refreshToken = ((UserContext) principal).getRefreshToken();
            }
        }

        return refreshToken;
    }
}
