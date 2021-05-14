package com.skhu.usertraders.exception;

public class ApiIllegalStateException extends IllegalStateException {
    public ApiIllegalStateException(final String msg) {
        super("Exception : " + msg);
    }
}
