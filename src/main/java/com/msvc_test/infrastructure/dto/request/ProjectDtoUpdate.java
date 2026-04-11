package com.msvc_test.infrastructure.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoUpdate implements Serializable {
    @NotNull
    Long id;
    @NotNull
    @NotBlank
    String projectName;
    @NotNull
    @NotBlank
    String clientName;
    @NotNull
    @NotBlank
    String description;
}