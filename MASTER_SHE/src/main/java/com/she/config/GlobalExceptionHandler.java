package com.she.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.she.common.model.Log;
import com.she.common.service.LogService;
import com.she.config.model.ExceptionType;
import com.she.config.model.GlobalException;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.JwtExpiredTokenException;
import com.she.security.exception.RestAuthException;

/**
 * Controller에서 발생되는 Exception을 수집하여, 공통으로 처리하는 ControllerAdvice
 */

@PropertySource(value = { "classpath:application.properties" })
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private Environment environment;

    @Autowired
    private LogService logService;

    static final String SPRING_PROFILE_PRD = "prd";
    static final String SPRING_PROFILE_DEV = "dev";
    static final String SPRING_PROFILE_TEST = "test";
    static final String CAUSE_KEY = "CAUSE_ERR";
    static final String CAUSE_DETAIL_KEY = "CAUSE_DETAIL_ERR";
    static final String COMMON_ERR_MSG = "작업 중 오류가 발생했습니다. 재시도 후 지속적인 문제 발생 시 관리자에게 문의하세요.";
    static final String AUTH_ERR_MSG = "인증 처리중 오류가 발생했습니다. 재로그인 시도 후에도 지속적인 문제가 발생 시 관리자에게 문의하세요.";

    /**
     * 스프링 프로필 설정에 따라서 사용자 메시지 혹은 개발자 메시지를 분리하여 처리해 주는 함수
     * 
     * @param exceptionCause
     * @param exceptionCauseDetail
     * @param userMsg
     * @return
     */
    private Map<String, String> makeErrorMsg(String exceptionCause, String exceptionCauseDetail, String userMsg) {

        String springProfiles = environment.getRequiredProperty("spring.profiles.active");
        Map<String, String> map = new HashMap<String, String>();

        String errorCause = "";
        String errorCauseDetail = "";

        if (SPRING_PROFILE_DEV.equalsIgnoreCase(springProfiles)) {
            errorCause = exceptionCause;
            errorCauseDetail = exceptionCauseDetail;
        } else if (SPRING_PROFILE_PRD.equalsIgnoreCase(springProfiles)) {
            errorCause = exceptionCause;
            errorCauseDetail = exceptionCauseDetail;
        } else {
            errorCause = userMsg;
            errorCauseDetail = userMsg;
        }

        map.put(CAUSE_KEY, errorCause);
        map.put(CAUSE_DETAIL_KEY, errorCauseDetail);
        return map;
    }

    @ExceptionHandler(value = RestAuthException.class)
    public ResponseEntity<GlobalException> handleRestException(HttpServletRequest request, HttpServletResponse response,
            RestAuthException e) {
        logger.debug("Raised UNAUTHORIZED Exception.");

        Map<String, String> map = this.makeErrorMsg(
                e.getCause() != null ? e.getCause().toString() : e.getErrorMessage(),
                e.getMessage() != null ? e.getMessage() : e.getErrorMessage(), AUTH_ERR_MSG);

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.UNAUTHORIZED)
                .setMessage(e.getErrorMessage()).setUrl(request.getRequestURI())
                .setQueryString(request.getQueryString()).setCause(map.get(CAUSE_KEY))
                .setCauseDetail(map.get(CAUSE_DETAIL_KEY)).setReturnCode(e.getReturnCode()).build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                e.getCause() != null ? e.getCause().toString() : e.getErrorMessage(),
                e.getMessage() != null ? e.getMessage() : e.getErrorMessage(), exception.getTimestamp().toString());

        int logNo = logService.createLog(log);
        exception.updateLogNo(logNo);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.UNAUTHORIZED);
    }

    /**
     * HttpErrorConfig에서 설정된 예외(403, 404, 500 등)를 제외한 나머지 exception을 처리
     * 
     * @param request
     *            : 요청 정보
     * @param response
     * @param e
     *            : 예외 정보
     * @return 예외정보 모델 클래스
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<GlobalException> handleAnyException(HttpServletRequest request, HttpServletResponse response,
            Exception e) {

        Map<String, String> map = this.makeErrorMsg(e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                COMMON_ERR_MSG);

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.INTERNAL_SERVER_ERROR)
                .setMessage(ExceptionType.INTERNAL_SERVER_ERROR.getMessage()).setUrl(request.getRequestURI())
                .setQueryString(request.getQueryString()).setCause(map.get(CAUSE_KEY))
                .setCauseDetail(map.get(CAUSE_DETAIL_KEY)).build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                exception.getTimestamp().toString());

        int logNo = logService.createLog(log);
        exception.updateLogNo(logNo);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<GlobalException> handleBadCredentialsException(HttpServletRequest request,
            HttpServletResponse response, BadCredentialsException e) {

        Map<String, String> map = this.makeErrorMsg(e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                AUTH_ERR_MSG);

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.UNAUTHORIZED)
                .setMessage(ExceptionType.UNAUTHORIZED.getMessage()).setCause(map.get(CAUSE_KEY))
                .setCauseDetail(map.get(CAUSE_DETAIL_KEY)).setReturnCode(ExceptionCode.INVALID.errorCode()).build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                exception.getTimestamp().toString());

        int logNo = logService.createLog(log);
        exception.updateLogNo(logNo);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = CredentialsExpiredException.class)
    public ResponseEntity<GlobalException> handleCredentialsExpiredException(HttpServletRequest request,
            HttpServletResponse response, CredentialsExpiredException e) {

        Map<String, String> map = this.makeErrorMsg(e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                COMMON_ERR_MSG);

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.UNAUTHORIZED)
                .setMessage("사용자 인증에 실패했습니다. 토큰이 만료되었습니다.").setCause(map.get(CAUSE_KEY))
                .setCauseDetail(map.get(CAUSE_DETAIL_KEY)).setReturnCode(e.getMessage()).build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                exception.getTimestamp().toString());

        int logNo = logService.createLog(log);
        exception.updateLogNo(logNo);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JwtExpiredTokenException.class)
    public ResponseEntity<GlobalException> handleJwtExpiredTokenException(HttpServletRequest request,
            HttpServletResponse response, JwtExpiredTokenException e) {

        Map<String, String> map = this.makeErrorMsg(e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                AUTH_ERR_MSG);

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.UNAUTHORIZED)
                .setMessage("사용자 인증에 실패했습니다. 토큰이 만료되었습니다.").setCause(map.get(CAUSE_KEY))
                .setCauseDetail(map.get(CAUSE_DETAIL_KEY)).setReturnCode(e.getMessage()).build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                e.getCause() != null ? e.getCause().toString() : "", e.getMessage(),
                exception.getTimestamp().toString());

        int logNo = logService.createLog(log);
        exception.updateLogNo(logNo);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.UNAUTHORIZED);
    }

}
