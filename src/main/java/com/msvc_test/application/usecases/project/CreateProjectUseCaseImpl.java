package com.msvc_test.application.usecases.project;

import com.msvc_test.domain.exceptions.ProjectExistException;
import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.input.CreateProjectUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;

    @Override
    public Project createProject(Project project) {
        if(projectRepositoryPort.existsByProjectName(project.getProjectName())){
            throw new ProjectExistException("The project with name " + project.getProjectName() + " already exists");
        }
        return projectRepositoryPort.save(project);
    }
}
