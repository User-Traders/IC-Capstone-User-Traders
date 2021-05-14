package com.skhu.usertraders.exception.board;


//광범위한 RuntimeException중 IllegalStateException
public class ApiIllegalStateException extends IllegalStateException {
    public ApiIllegalStateException(final String msg) {
        super("Exception : " + msg);
    }
}
