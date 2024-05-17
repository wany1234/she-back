package com.she.config.model;

/**
 * 사용자 정의 Exception 형식
 */
public enum ExceptionType {

    // SQL Error
    SQL("SQL_ERROR", "데이터 처리 중 오류가 발생하였습니다. 재시도 후 지속적인 문제 발생 시 관리자에게 문의하세요."),

    // 서버 오류 (사용 안 될 수도 있음)
    SERVER("SERVER_EXCEPTION", "서버에서 예외가 발생하였습니다. 재시도 후 지속적인 문제 발생 시 관리자에게 문의하세요."),

    // 401
    UNAUTHORIZED("UNAUTHORIZED", "인증되지 않은 정보입니다."),

    // 403 Forbidden : 서버가 허용하지 않는 웹 페이지나 미디어를 요청시 발생하는 오류
    FORBIDDEN("FORBIDDEN", "허용되지 않은 정보를 요청하고 있습니다."),

    // 404 Not Found : 서비스가 없을 경우
    NOT_FOUND("NOT_FOUND", "서비스 처리 중 오류가 발생했습니다. 재시도 후 지속적인 문제 발생 시 관리자에게 문의하세요."),

    // 500 Internal Server Error : 서비스 처리 도중 에러 발생
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "작업 중 오류가 발생했습니다. 재시도 후 지속적인 문제 발생 시 관리자에게 문의하세요.");



    private final String code;

    private final String message;

    ExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
