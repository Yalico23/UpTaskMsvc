package com.msvc_test.application.service;

import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.input.CreateProjectUseCase;
import com.msvc_test.domain.port.input.DeleteProjectUseCase;
import com.msvc_test.domain.port.input.ListProjectsUseCase;
import com.msvc_test.domain.port.input.UpdateProjectUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService implements CreateProjectUseCase, ListProjectsUseCase, DeleteProjectUseCase, UpdateProjectUseCase {

    private final CreateProjectUseCase createProjectUseCase;
    private final ListProjectsUseCase listProjectsUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;

    public ProjectService(CreateProjectUseCase createProjectUseCase, ListProjectsUseCase listProjectsUseCase, DeleteProjectUseCase deleteProjectUseCase, UpdateProjectUseCase updateProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.listProjectsUseCase = listProjectsUseCase;
        this.deleteProjectUseCase = deleteProjectUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
    }

    @Transactional
    @Override
    public Project createProject(Project project) {
        return createProjectUseCase.createProject(project);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> listProjects() {
        return listProjectsUseCase.listProjects();
    }

    @Transactional(readOnly = true)
    @Override
    public Project getProjectById(Long id) {
        return listProjectsUseCase.getProjectById(id);
    }

    @Transactional
    @Override
    public void deleteProject(Long id) {
        deleteProjectUseCase.deleteProject(id);
    }

    @Transactional
    @Override
    public Project updateProject(Project project) {
        return updateProjectUseCase.updateProject(project);
    }
}
