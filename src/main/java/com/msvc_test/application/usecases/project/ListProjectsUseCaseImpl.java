package com.msvc_test.application.usecases.project;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.input.project.ListProjectsUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.infrastructure.configuration.auth.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class ListProjectsUseCaseImpl implements ListProjectsUseCase {

    private final ProjectRepositoryPort repositoryPort;
    private final SecurityUtils securityUtils;

    @Override
    public List<Project> listProjects() {
        return repositoryPort.findAll();
    }

    @Override
    public List<Project> listProjectsByUserId() {
        UUID userId = securityUtils.getCurrentUserId();
        return repositoryPort.findAllByUserId(userId);
    }

    @Override
    public Project getProjectById(Long id) {
        if(Objects.isNull(id) || id == 0){
            throw new IllegalArgumentException("Project id cannot be null or empty");

        }
        return repositoryPort.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));
    }
}
