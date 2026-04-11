package com.msvc_test.domain.port.input;

import com.msvc_test.domain.models.Project;

import java.util.List;

public interface ListProjectsUseCase {
    List<Project> listProjects();
    Project getProjectById(Long id);
}
