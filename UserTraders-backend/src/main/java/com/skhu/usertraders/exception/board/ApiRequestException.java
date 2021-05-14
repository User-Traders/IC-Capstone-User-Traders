package com.skhu.usertraders.exception.board;


//그냥 광범위한 RuntimeException 발생시
public class ApiRequestException extends RuntimeException{
    public ApiRequestException(final String msg) {
        super("Exception : " + msg);
    }
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
