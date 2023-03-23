package com.awasik.gameserver.model;
public class MyResponse {
    private String method;
    private String message;

    public MyResponse(String method, String message) {
        this.method = method;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

