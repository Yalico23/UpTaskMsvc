package com.msvc_test.domain.exceptions;

public class ProjectExistException extends RuntimeException {
    public ProjectExistException(String message) {
        super(message);
    }
}
