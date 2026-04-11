package com.msvc_test.infrastructure.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoCreate implements Serializable {
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