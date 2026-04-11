package com.msvc_test.application.usecases.project;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.port.input.DeleteProjectUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {

    private final ProjectRepositoryPort repositoryPort;

    @Override
    public void deleteProject(Long id) {
        if(Objects.isNull(id) || id <= 0){
            throw new IllegalArgumentException("Invalid project ID");
        }
        if(repositoryPort.findById(id).isEmpty()){
            throw new ProjectNotFoundException("Project with ID " + id + " not found");
        }
        repositoryPort.deleteById(id);
    }
}
