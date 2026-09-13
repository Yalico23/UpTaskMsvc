package com.msvc_test.domain.port.output;

import com.msvc_test.domain.models.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepositoryPort {
    Project save(Project project);
    Optional<Project> findById(Long id);
    boolean existsByProjectName(String projectName);
    boolean existsById(Long id);
    List<Project> findAll();
    List<Project> findAllByUserId(UUID userId);
    void deleteById(Long id);
}
