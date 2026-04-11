package com.msvc_test.infrastructure.repositories;

import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.infrastructure.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProjectEntityAdapter implements ProjectRepositoryPort {

    private final ProjectEntityRepository repository;
    private final ProjectMapper mapper;

    @Override
    public Project save(Project project) {
        return mapper.toModel(repository.save(mapper.toEntity(project)));
    }

    @Override
    public Optional<Project> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public boolean existsByProjectName(String projectName) {
        return repository.existsByProjectName(projectName);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Project> findAll() {
        return mapper.toModel(repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
