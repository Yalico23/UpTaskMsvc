package com.msvc_test.domain.port.input.project;

import com.msvc_test.domain.models.Project;

import java.util.UUID;

public interface CreateProjectUseCase {
    Project createProject (Project project);
}
