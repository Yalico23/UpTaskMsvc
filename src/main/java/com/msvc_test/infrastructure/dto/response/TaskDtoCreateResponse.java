package com.msvc_test.infrastructure.dto.response;

import com.msvc_test.domain.models.TaskStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDtoCreateResponse implements Serializable {
    Long id;
    String name;
    String description;
    TaskStatus taskStatus;
    Long projectId;
}