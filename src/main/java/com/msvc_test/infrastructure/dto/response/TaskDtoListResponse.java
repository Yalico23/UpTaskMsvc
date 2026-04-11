
package com.msvc_test.infrastructure.dto.response;

import com.msvc_test.domain.models.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDtoListResponse implements Serializable {
    Long id;
    String name;
    String description;
    TaskStatus taskStatus;
}