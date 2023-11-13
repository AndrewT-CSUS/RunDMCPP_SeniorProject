package com.RunDMCPP.Backend.utils;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

public class BackendErrorException extends Exception {
    private HttpStatus httpStatus;
    private ErrorEnum errorEnum;

    public BackendErrorException(HttpStatus status, ErrorEnum errorEnum) {
        this.httpStatus = status;
        this.errorEnum = errorEnum;
    }

    public BackendErrorException(ErrorEnum errorEnum) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorEnum = errorEnum;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public ErrorEnum getErrorEnum(){
        return this.errorEnum;
    }
}


