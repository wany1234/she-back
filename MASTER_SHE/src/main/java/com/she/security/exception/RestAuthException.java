package com.she.security.exception;

import org.springframework.http.HttpStatus;

public class RestAuthException extends Exception {

    private static final long serialVersionUID = 1L;


    private HttpStatus errorCode;
    private String errorMessage;
    private String returnCode;


    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public RestAuthException(HttpStatus errorCode, String errorMessage, String returnCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.returnCode = returnCode;
    }

    public RestAuthException() {
        super();
    }
}
