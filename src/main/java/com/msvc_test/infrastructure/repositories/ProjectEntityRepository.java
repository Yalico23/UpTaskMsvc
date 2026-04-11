package com.msvc_test.infrastructure.repositories;

import com.msvc_test.infrastructure.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, Long> {
    boolean existsByProjectName(String projectName);
}