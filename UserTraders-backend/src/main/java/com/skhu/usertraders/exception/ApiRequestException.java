package com.skhu.usertraders.exception;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException(final String msg) {
        super("Exception : " + msg);
    }
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
