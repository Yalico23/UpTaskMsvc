package com.msvc_test.infrastructure.mapper;

import com.msvc_test.domain.models.Task;
import com.msvc_test.infrastructure.dto.request.TaskDtoCreate;
import com.msvc_test.infrastructure.dto.request.TaskDtoUpdate;
import com.msvc_test.infrastructure.dto.response.TaskDtoCreateResponse;
import com.msvc_test.infrastructure.dto.response.TaskDtoListResponse;
import com.msvc_test.infrastructure.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "project.tasks", ignore = true)
    @Mapping(target = "project.user", ignore = true)
    Task toModel (TaskEntity taskEntity);

    TaskEntity toEntity (Task task);

    List<Task> toModel(List<TaskEntity> taskEntities);

    Task toModel(TaskDtoCreate taskDtoCreate);

    Task toModel(TaskDtoUpdate taskDtoCreate);

    @Mapping(source = "project.id", target = "projectId") // ✅ MapStruct extrae solo el ID
    TaskDtoCreateResponse toResponse(Task task);

    TaskDtoListResponse toListResponse(Task task);
}
