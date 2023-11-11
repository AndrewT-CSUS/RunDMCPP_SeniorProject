package com.RunDMCPP.Backend.enums;

public enum EventEnum {
    NOT_FOUND_E("Error: Event not found."),
    /*
    This is just here for testing to see if we can use korean text, not sure if we want to do this or if we want to
    put the work on the front end. IMO we should just return custom error codes and let the FE convert to whatever language
    the end user is working with
    (i.e. 1 - not found, 2 - transaction fail)
    */
    NOT_FOUND_K("오류: 설교를 찾을 수 없습니다."),
    TRANSACTION_FAIL_E("Error: Could not complete transaction."),
    DATA_MISMATCH("Error: Data mismatch. Can not delete.");
    private final String string;

    EventEnum(String string){
        this.string = string;
    }

    public String getValue(){
        return this.string;
    }
}
