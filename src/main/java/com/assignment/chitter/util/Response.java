package com.assignment.chitter.util;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Response created to send data in the form of JSON
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private String status = "FAILED";
    private Object data;
    private String message;

    public Response() {
    }

    public Response(String message){
        this.message = message;
    }
    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public Response(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

