package com.msvc_test.domain.port.output;

import com.msvc_test.domain.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(Long id);
    void deleteById(Long id);
    List<Task> findByProjectId(Long projectId);
    boolean existsById(Long id);
}
