package com.she.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.she.common.model.Log;
import com.she.common.service.LogService;
import com.she.config.model.ExceptionType;
import com.she.config.model.GlobalException;

/**
 * SQL 처리 단계에서 발생하는 예외를 처리하는 클래스
 */

@PropertySource(value = { "classpath:application.properties" })
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalSqlExceptionHandler {

    @Autowired
    private Environment environment;

    @Autowired
    private LogService logService;

    static final String SPRING_PROFILE_PRD = "prd";
    static final String SPRING_PROFILE_DEV = "dev";
    static final String SPRING_PROFILE_TEST = "test";
    static final String CAUSE_KEY = "CAUSE_ERR";
    static final String CAUSE_DETAIL_KEY = "CAUSE_DETAIL_ERR";

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

    /**
     * SQL에서 발생하는 Exception을 수집하여 처리하는 메써드
     * 
     * @param request
     *            : 요청 정보
     * @param e
     *            : 예외 정보
     * @return 예외정보 모델 클래스
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<GlobalException> sqlException(HttpServletRequest request, Exception e) {

        Map<String, String> map = this.makeErrorMsg(e.getCause().toString(), e.getMessage(),
                "요청 처리중 오류가 발생했습니다. 관리자에게 문의해 주세요.");

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.INTERNAL_SERVER_ERROR)
                .setMessage(ExceptionType.INTERNAL_SERVER_ERROR.getMessage()).setUrl(request.getRequestURI())
                .setQueryString(request.getQueryString()).setCause(map.get(CAUSE_KEY))
                .setCauseDetail(map.get(CAUSE_DETAIL_KEY)).build();

        Log log = new Log("sql error", exception.getMessage(), exception.getCode().getCode(),
                exception.getQueryString(), e.getCause().toString(), e.getMessage(),
                exception.getTimestamp().toString());

        int logNo = logService.createLog(log);
        exception.updateLogNo(logNo);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
