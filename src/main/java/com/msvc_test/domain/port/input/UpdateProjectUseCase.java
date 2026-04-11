package com.msvc_test.domain.port.input;

import com.msvc_test.domain.models.Project;

public interface UpdateProjectUseCase {
    Project updateProject(Project project);
}
