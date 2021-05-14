package com.skhu.usertraders.exception.board;


//광범위한 RuntimeException중 IllegalStateException
public class ApiIllegalArgumentException extends IllegalArgumentException {
    public ApiIllegalArgumentException(final String msg) {
        super("Exception : " + msg);
    }
}
