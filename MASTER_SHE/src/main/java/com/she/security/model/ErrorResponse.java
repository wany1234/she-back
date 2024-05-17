package com.she.security.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * Error model for interacting with client.
 *
 * @author vladimir.stankovic
 *
 *         Aug 3, 2016
 */
public class ErrorResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // HTTP Response Status Code
    private HttpStatus status;

    // General Error message
    private String message;
    private String errorMessage;

    // Error code
    private ErrorCode errorCode;

    private Date timestamp;
    private String returnCode;

    public ErrorResponse(String message, ErrorCode errorCode, String returnCode, HttpStatus status) {
        super();
        this.message = message;
        this.errorMessage = message;
        this.errorCode = errorCode;
        this.status = status;
        this.returnCode = returnCode;
        this.timestamp = new java.util.Date();
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

}
