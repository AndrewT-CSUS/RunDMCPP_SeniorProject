package com.RunDMCPP.Backend.utils;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

public class BackendErrorResponse{
    private HttpStatus httpStatus;
    private int code;
    private String message;


    public BackendErrorResponse(BackendErrorException e) {
        this.httpStatus = e.getHttpStatus();
        this.code = e.getErrorEnum().getCode();
        this.message = e.getErrorEnum().getMessage();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public int getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }


}


