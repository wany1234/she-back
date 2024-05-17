package com.she.security.exception;

public class RestAuthErrorResponse implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int statusCode;
    private String errorMessage;
    private String returnCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return "RestAuthErrorResponse [statusCode= " + statusCode + ", errorMessage= " + errorMessage + ", returnCode= "
                + returnCode + "]";
    }

}
