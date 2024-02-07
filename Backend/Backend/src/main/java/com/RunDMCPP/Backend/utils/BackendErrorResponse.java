package com.RunDMCPP.Backend.utils;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

// BackendErrorException. This class represents an error thrown from the backend.
public class BackendErrorResponse{ 
    private HttpStatus httpStatus; // HTTP status code
    private int code;              // Error code
    private String message;        // Error message

    // Constructor takes BackendErrorException & sets up reponse
    public BackendErrorResponse(BackendErrorException e) {
        this.httpStatus = e.getHttpStatus();
        this.code = e.getErrorEnum().getCode();
        this.message = e.getErrorEnum().getMessage();
    }

    // Getters
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


