package com.RunDMCPP.Backend.testData;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import org.springframework.http.HttpStatus;

public class TestExceptionData {

    public static BackendErrorException backendError_NotFound(){
        return new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
    }
    public static BackendErrorException backendError_InvalidInput(){
        return new BackendErrorException(ErrorEnum.INVALID_INPUT);
    }
}
