package com.she.security.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class RestOkResponse implements Serializable {

    public final long serialVersionUID = 1L;

    public int statusCode = HttpStatus.OK.value();
    public String returnMessage = "success";
    public Map<String, String> returnResult = new HashMap<String, String>();

    public RestOkResponse() {
        super();
    }

    public RestOkResponse(Map<String, String> returnResult) {
        super();
        this.returnResult = returnResult;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Map<String, String> getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(Map<String, String> returnResult) {
        this.returnResult = returnResult;
    }

}