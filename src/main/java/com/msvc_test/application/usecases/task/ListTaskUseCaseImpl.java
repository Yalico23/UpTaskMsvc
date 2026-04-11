package com.msvc_test.application.usecases.task;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.exceptions.TaskNotFound;
import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.port.input.ListTaskUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.domain.port.output.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ListTaskUseCaseImpl implements ListTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;

    @Override
    public List<Task> listTasks(Long projectId) {
        if(Objects.isNull(projectId) || projectId <= 0){
            throw new IllegalArgumentException("Project ID must not be null or less than or equal to zero");
        }
        if(!projectRepositoryPort.existsById(projectId)){
            throw new ProjectNotFoundException("Project with ID " + projectId + " not found");
        }
        return taskRepositoryPort.findByProjectId(projectId);
    }

    @Override
    public Task listTaskById(Long id) {
        if(Objects.isNull(id) || id <= 0){
            throw new IllegalArgumentException("Task ID must not be null or less than or equal to zero");
        }
        return taskRepositoryPort.findById(id).orElseThrow(()-> new TaskNotFound("Task with ID " + id + " not found"));
    }
}
