package com.msvc_test.domain.models;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR_004", "Un error inesperado ocurrió"),
    INVALID_INPUTS("INVALID_INPUTS_001", "Los datos proporcionados son inválidos"),
    PROJECT_EXIST("PROJECT_EXIST_002", "El proyecto ya existe"),
    PROJECT_NOT_FOUND("PROJECT_NOT_FOUND_003", "El proyecto no existe"),
    EMAIL_SEND_ERROR("EMAIL_SEND_ERROR_005", "Error al enviar el correo electrónico");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
