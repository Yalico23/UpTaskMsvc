package com.msvc_test.infrastructure.mapper;

import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.models.User;
import com.msvc_test.infrastructure.dto.request.ProjectDtoCreate;
import com.msvc_test.infrastructure.dto.request.ProjectDtoUpdate;
import com.msvc_test.infrastructure.entities.ProjectEntity;
import com.msvc_test.infrastructure.entities.TaskEntity;
import com.msvc_test.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    // Como Task es una lista dentro de Project, necesitamos mapearla también. Para evitar la recursión infinita, usamos un método separado con @Named para mapear TaskEntity a Task sin incluir el proyecto.
    @Mapping(target = "tasks", source = "tasks", qualifiedByName = "taskWithoutProject")
    @Mapping(target = "user", source = "user", qualifiedByName = "userWithoutProjects")
    Project toModel(ProjectEntity project);

    @Named("taskWithoutProject")// Evitamos la recursión infinita al mapear TaskEntity a Task sin incluir el proyecto
    @Mapping(target = "project", ignore = true) // Ignoramos la relación inversa para evitar la recursión infinita
    Task taskEntityToTask(TaskEntity taskEntity);

    @Named("userWithoutProjects")// Evitamos la recursión infinita al mapear UserEntity a User sin incluir los proyectos
    @Mapping(target = "projects", ignore = true) // Ignoramos la relación invers
    User userWithoutProjects(UserEntity userEntity);


    ProjectEntity toEntity(Project projectEntity);

    List<Project> toModel(List<ProjectEntity> projectEntities);

    Project toModel(ProjectDtoCreate projectDtoCreate);

    Project toModel(ProjectDtoUpdate dtoUUpdate);
}
