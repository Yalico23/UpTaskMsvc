package com.msvc_test.infrastructure.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDtoCreate implements Serializable {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String description;
}