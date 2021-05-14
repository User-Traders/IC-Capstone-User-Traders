package com.skhu.usertraders.exception.board;

import javassist.NotFoundException;

//url 접근시 잘못된 url
public class ApiNotFoundEexception extends NotFoundException {
    public ApiNotFoundEexception(final String msg) {
        super(msg);
    }
}
