package com.msvc_test.infrastructure.dto.request;

import com.msvc_test.domain.models.TaskStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusDtoUpdate {
    @NotNull
    @Min(1)
    Long id;
    @NotNull
    TaskStatus taskStatus;
}
