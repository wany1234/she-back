package com.she.security.exception;

public enum ExceptionCode {
    ACCESS_EXPIRED("ACCESS_EXPIRED"),
    REFRESH_EXPIRED("REFRESH_EXPIRED"),
    INVALID("INVALID"),
    UNAUTHORIZED("UNAUTHORIZED"),
    WRONG_PASSWORD("WRONG_PASSWORD"),
    CONCURRENT_EXPIRED("CONCURRENT_EXPIRED"),
    WRONG_TENANT("WRONG_TENANT");

    private String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String errorCode() {
        return errorCode;
    }
}
