package com.msvc_test.infrastructure.dto.request;

import com.msvc_test.domain.models.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDtoUpdate implements Serializable {
    @NotNull
    Long id;
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String description;
    @NotNull
    TaskStatus taskStatus;
}