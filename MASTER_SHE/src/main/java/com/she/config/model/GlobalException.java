package com.she.config.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Exception에 대한 정보를 지정된 형식으로 나타내기 위한 모델 클래스 19.09.02 김유경 : 생성자가 다중으로 발생되어, 생성자
 * 빌드패턴으로 오브젝트 구성
 */
public class GlobalException {
    // 사용자 정의 예외 형식
    private ExceptionType code;

    // 예외 메시지
    private String message;

    // 예외 발생 url
    private String url;

    // 예외 querystring
    private String queryString;

    // 예외 원인 정보
    private String cause;

    // 예외 상세 정보
    private String causeDetail;

    // 예외 발생 시간
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    // 예외반환코드
    private String returnCode;

    private Integer logNo;

    public static class Builder {
        private ExceptionType code = ExceptionType.INTERNAL_SERVER_ERROR;
        private String message = "";
        private String url = "";
        private String queryString = "";
        private String cause = "";
        private String causeDetail = "";
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime timestamp = LocalDateTime.now();
        private String returnCode = "";
        private Integer logNo;

        public Builder() {
        }

        public Builder setCode(ExceptionType v) {
            this.code = v;
            return this;
        }

        public Builder setMessage(String v) {
            this.message = v;
            return this;
        }

        public Builder setUrl(String v) {
            this.url = v;
            return this;
        }

        public Builder setQueryString(String v) {
            this.queryString = v;
            return this;
        }

        public Builder setCause(String v) {
            this.cause = v;
            return this;
        }

        public Builder setCauseDetail(String v) {
            this.causeDetail = v;
            return this;
        }

        public Builder setTimeStamp(LocalDateTime v) {
            this.timestamp = v;
            return this;
        }

        public Builder setReturnCode(String v) {
            this.returnCode = v;
            return this;
        }

        public Builder setLogNo(Integer v) {
            this.logNo = v;
            return this;
        }

        public GlobalException build() {
            return new GlobalException(this);
        }
    }

    private GlobalException(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.url = builder.url;
        this.queryString = builder.queryString;
        this.cause = builder.cause;
        this.causeDetail = builder.causeDetail;
        this.timestamp = builder.timestamp;
        this.returnCode = builder.returnCode;
        this.logNo = builder.logNo;
    }

    @Override
    public String toString() {
        return code + ", " + code.getCode() + ": " + cause;
    }

    public ExceptionType getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public String getCause() {
        return cause;
    }

    public String getCauseDetail() {
        return causeDetail;
    }

    public String getQueryString() {
        return queryString;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public Integer getLogNo() {
        return logNo;
    }

    public void updateLogNo(Integer logNo) {
        this.logNo = logNo;
    }
}
