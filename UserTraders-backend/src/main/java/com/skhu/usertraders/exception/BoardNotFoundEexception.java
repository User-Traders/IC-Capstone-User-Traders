package com.skhu.usertraders.exception;

import javassist.NotFoundException;

public class BoardNotFoundEexception extends NotFoundException {
    public BoardNotFoundEexception(final String msg) {
        super(msg);
    }
}
