package com.she.config.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.Log;
import com.she.common.service.LogService;
import com.she.config.model.ExceptionType;
import com.she.config.model.GlobalException;

/**
 * 404, 405, 500 에러 처리 특이사항 : HttpErrorConfiguration에서 정의된 ErrorPage가 redirect
 * 되어 여기로 오게 됨
 */
@RestController
public class ExceptionController {

    @Autowired
    private LogService logService;

    /**
     * 404, 405, 500 에러를 제외한 예외 처리 특이사항 : 이 메써드는 호출될 필요가 없을 것 같은데 조금 더 테스트 해보고
     * 수정할 예정
     * 
     * @param request
     * @return 예외정보 모델 클래스
     */
    @RequestMapping(value = "/handling", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<GlobalException> handleCustomException(HttpServletRequest request) {

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.INTERNAL_SERVER_ERROR)
                .setMessage(ExceptionType.INTERNAL_SERVER_ERROR.getMessage()).setUrl(request.getRequestURI())
                .setQueryString(request.getQueryString()).setCause(request.getAttribute("cause").toString())
                .setCauseDetail(request.getAttribute("message").toString()).build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                exception.getCause(), exception.getMessage(), exception.getTimestamp().toString());

        int logNo = logService.createLog(log);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 403 FORBIDDEN 에러 처리
     * 
     * @return 예외정보 모델 클래스
     */
    @RequestMapping("/403")
    public ResponseEntity<GlobalException> forbidden() {

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.FORBIDDEN)
                .setMessage(ExceptionType.FORBIDDEN.getMessage()).setCause(HttpStatus.FORBIDDEN.getReasonPhrase())
                .build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                exception.getCause(), exception.getTimestamp().toString());

        int logNo = logService.createLog(log);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.FORBIDDEN);
    }

    /**
     * 404 NOT FOUND 에러 처리
     * 
     * @return 예외정보 모델 클래스
     */
    @RequestMapping("/404")
    public ResponseEntity<GlobalException> notFound() {

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.NOT_FOUND)
                .setMessage(ExceptionType.NOT_FOUND.getMessage()).setCause(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();

        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                exception.getCause(), exception.getTimestamp().toString());

        int logNo = logService.createLog(log);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.NOT_FOUND);
    }

    /**
     * 500 INTERNAL_SERVER_ERROR 에러 처리
     * 
     * @return 예외 모델 클래스
     */
    @RequestMapping("/500")
    public ResponseEntity<GlobalException> internalServerError() {

        GlobalException exception = new GlobalException.Builder().setCode(ExceptionType.INTERNAL_SERVER_ERROR)
                .setMessage(ExceptionType.INTERNAL_SERVER_ERROR.getMessage())
                .setCause(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        Log log = new Log("error", exception.getMessage(), exception.getCode().getCode(), exception.getQueryString(),
                exception.getCause(), exception.getTimestamp().toString());

        int logNo = logService.createLog(log);

        return new ResponseEntity<GlobalException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
