package com.corelogic.propertymanager.utilities;


public class JsonResponseBody {

    private  int responseCode;
    private Object response;

    public JsonResponseBody() {
    }

    public JsonResponseBody(int responseCode) {
        this.responseCode = responseCode;
    }

    public JsonResponseBody(int responseCode, Object response) {
        this.responseCode = responseCode;
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}