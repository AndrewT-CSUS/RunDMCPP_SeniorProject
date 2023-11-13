package com.RunDMCPP.Backend.enums;

public enum ErrorEnum {

    NOT_FOUND(1 , "Error: Data can not be found in database "),
    DATA_MISMATCH(2 , "Error: Data in database does not match provided data"),
    INVALID_INPUT(3 , "Error: Input is not valid"),
    TRANSACTION_FAIL(4, "Error: Could not complete transaction.");

    private final int code;
    private final String message;
    ErrorEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

}
