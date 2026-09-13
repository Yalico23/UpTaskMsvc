package com.msvc_test.application.usecases.project;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.input.project.DeleteProjectUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.infrastructure.configuration.auth.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {

    private final ProjectRepositoryPort repositoryPort;
    private final SecurityUtils securityUtils;

    @Override
    public void deleteProject(Long id) {
        if(Objects.isNull(id) || id <= 0){
            throw new IllegalArgumentException("Invalid project ID");
        }
        Project existing = repositoryPort.findById(id)
                .orElseThrow(()-> new ProjectNotFoundException("Project with ID " + id + " not found"));

        if(!securityUtils.getCurrentUserId().equals(existing.getUser().getId())){
            throw new RuntimeException("Only the owner of the project can delete it");
        }

        repositoryPort.deleteById(id);
    }
}
