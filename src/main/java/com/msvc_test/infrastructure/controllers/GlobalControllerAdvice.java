package com.msvc_test.infrastructure.controllers;

import com.msvc_test.domain.exceptions.EmailSendException;
import com.msvc_test.domain.exceptions.ProjectExistException;
import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.models.ErrorCatalog;
import com.msvc_test.domain.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProjectExistException.class)
    public ErrorResponse handleProjectExistException(ProjectExistException exception) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.PROJECT_EXIST.getCode())
                .message(ErrorCatalog.PROJECT_EXIST.getMessage())
                .details(List.of(exception.getMessage()))
                .timestamp(LocalDate.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProjectNotFoundException.class)
    public ErrorResponse handleProjectNotFound (ProjectNotFoundException exception) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.PROJECT_NOT_FOUND.getCode())
                .message(ErrorCatalog.PROJECT_NOT_FOUND.getMessage())
                .details(List.of(exception.getMessage()))
                .timestamp(LocalDate.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmailSendException.class)
    public ErrorResponse handleEmailSendException(EmailSendException exception) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.EMAIL_SEND_ERROR.getCode())
                .message(ErrorCatalog.EMAIL_SEND_ERROR.getMessage())
                .details(List.of(exception.getMessage()))
                .timestamp(LocalDate.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleErrorResponse(MethodArgumentNotValidException exception) {

        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCatalog.INVALID_INPUTS.getCode())
                .message(ErrorCatalog.INVALID_INPUTS.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .toList())
                .timestamp(LocalDate.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception exception) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCatalog.INTERNAL_SERVER_ERROR.getMessage())
                .details(List.of(exception.getMessage()))
                .timestamp(LocalDate.now())
                .build();
    }

}
