package com.RunDMCPP.Backend.enums;

// Enum class (basically a list of constants) for error codes and messages
public enum ErrorEnum {
    // Each of these lines is an instance of the ErrorEnum with a unique code and message
    NOT_FOUND(1 , "Error: Data can not be found in database "),
    DATA_MISMATCH(2 , "Error: Data in database does not match provided data"),
    INVALID_INPUT(3 , "Error: Input is not valid"),
    TRANSACTION_FAIL(4, "Error: Could not complete transaction.");

    private final int code;       // Error code
    private final String message; // Error message

    // Constructor for ErrorEnum, sets the code and message
    ErrorEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    // Getters and setters for ErrorEnum
    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

}
