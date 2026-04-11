package com.msvc_test.application.usecases.project;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.input.ListProjectsUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ListProjectsUseCaseImpl implements ListProjectsUseCase {

    private final ProjectRepositoryPort repositoryPort;

    @Override
    public List<Project> listProjects() {
        return repositoryPort.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        if(Objects.isNull(id) || id == 0){
            throw new IllegalArgumentException("Project id cannot be null or empty");

        }
        return repositoryPort.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));
    }
}
