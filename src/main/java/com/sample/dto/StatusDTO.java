package com.sample.dto;

import java.util.ArrayList;
import java.util.List;

public class StatusDTO {
    private int code;
    private String message;
    private Object entity;
    private ArrayList<String> errorList;
    private List response = new ArrayList();

    public StatusDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public StatusDTO(int code, Object entity) {
        this.code = code;
        this.entity = entity;
    }
    public StatusDTO(int code, String message, Object entity) {
        this.code = code;
        this.message = message;
        this.entity = entity;
    }
    public StatusDTO(int code, String message, List response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }
    public StatusDTO(int code, ArrayList<String> errorList, Object entity) {
        this.code = code;
        this.errorList = errorList;
        this.entity = entity;
    }
    public StatusDTO(int code, ArrayList<String> errorList) {
        this.code = code;
        this.errorList = errorList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public ArrayList<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(ArrayList<String> errorList) {
        this.errorList = errorList;
    }

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }
}
