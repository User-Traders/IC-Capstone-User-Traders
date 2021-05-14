package com.skhu.usertraders.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionhandler {

    //400
    @ExceptionHandler(value = {RuntimeException.class,
            ApiRequestException.class})
    public ResponseEntity<?> handleApiRequestException(ApiRequestException e) {
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

    @ExceptionHandler(value = {IllegalStateException.class,
            ApiIllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(ApiIllegalStateException e) {
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

    //404
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleBoardNotFoundException(BoardNotFoundEexception e) {
        // 1. 먼저 exception 정보를 포함한 페이로드를 생성합니다.
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiExceptionInfo apiException = new ApiExceptionInfo(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //2. ResponseEntity 객체로 반환합니다.
        return new ResponseEntity<>(apiException, notFound);
    }

    // 401
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {

        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiExceptionInfo apiException = new ApiExceptionInfo(
                e.getMessage(),
                unauthorized,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, unauthorized);
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception e) {

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiExceptionInfo apiException = new ApiExceptionInfo(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, internalServerError);
    }
}
