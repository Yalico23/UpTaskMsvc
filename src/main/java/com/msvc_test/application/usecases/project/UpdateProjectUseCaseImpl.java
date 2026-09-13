package com.msvc_test.application.usecases.project;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.input.project.UpdateProjectUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.infrastructure.configuration.auth.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UpdateProjectUseCaseImpl implements UpdateProjectUseCase {

    private final ProjectRepositoryPort repositoryPort;
    private final SecurityUtils securityUtils;

    @Override
    public Project updateProject(Project project) {
        if(Objects.isNull(project) || Objects.isNull(project.getId())){
            throw new IllegalArgumentException("Project and Project ID must not be null");
        }
        Project existing = repositoryPort.findById(project.getId())
                .orElseThrow(()-> new ProjectNotFoundException("Project with ID " + project.getId() + " not found"));

        if(!securityUtils.getCurrentUserId().equals(existing.getUser().getId())){
            throw new RuntimeException("Only the owner of the project can update it");
        }

        existing.update(project);

        return repositoryPort.save(existing);
    }
}

