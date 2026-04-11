package com.msvc_test.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String message;
    private final List<String> details;
    private final LocalDate timestamp;

}
