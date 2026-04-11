package com.msvc_test.infrastructure.repositories;

import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.port.output.TaskRepositoryPort;
import com.msvc_test.infrastructure.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TaskEntityAdapter implements TaskRepositoryPort {

    private final TaskEntityRepository repository;
    private final TaskMapper mapper;

    @Override
    public Task save(Task task) {
        return mapper.toModel(repository.save(mapper.toEntity(task)));
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Task> findByProjectId(Long projectId) {
        return mapper.toModel(repository.findByProjectId(projectId));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
