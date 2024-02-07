package com.RunDMCPP.Backend.utils;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

// BackendErrorException. This class is used to throw errors from the backend.
public class BackendErrorException extends Exception {
    private HttpStatus httpStatus; // HTTP status code
    private ErrorEnum errorEnum;   // Error code

    // Constructor w/ HTTP status code and error code
    public BackendErrorException(HttpStatus status, ErrorEnum errorEnum) {
        this.httpStatus = status;   // Set HTTP status code
        this.errorEnum = errorEnum; // Set error code
    }

    // Constructor w/ error code only
    public BackendErrorException(ErrorEnum errorEnum) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorEnum = errorEnum;
    }

    // Getters
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public ErrorEnum getErrorEnum(){
        return this.errorEnum;
    }
}


