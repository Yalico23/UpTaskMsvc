package com.msvc_test.domain.port.input.project;

import com.msvc_test.domain.models.Project;

import java.util.List;

public interface ListProjectsUseCase {
    List<Project> listProjects();
    List<Project> listProjectsByUserId();
    Project getProjectById(Long id);
}
