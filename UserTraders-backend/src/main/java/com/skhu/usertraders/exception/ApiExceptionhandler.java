package com.skhu.usertraders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionhandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        // 1. 먼저 exception 정보를 포함한 페이로드를 생성합니다.
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionInfo apiException = new ApiExceptionInfo(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //2. ResponseEntity 객체로 반환합니다.
        return new ResponseEntity<>(apiException, badRequest);
    }
}
