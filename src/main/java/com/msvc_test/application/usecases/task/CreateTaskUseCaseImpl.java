package com.msvc_test.application.usecases.task;

import com.msvc_test.domain.exceptions.ProjectNotFoundException;
import com.msvc_test.domain.models.Project;
import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.port.input.task.CreateTaskUseCase;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.domain.port.output.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import static com.msvc_test.domain.models.TaskStatus.*;

import java.util.Objects;

@RequiredArgsConstructor
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;

    @Override
    public Task createTask(Task task, Long projectId) {
        if(Objects.isNull(task)){
            throw new RuntimeException("Task cannot be null");
        }
        if(Objects.isNull(projectId) || projectId <= 0){
            throw new IllegalArgumentException("Project ID must be a positive number");
        }
        Project project = projectRepositoryPort.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + projectId));
        task.setProject(project);
        task.setTaskStatus(PENDING);
        return taskRepositoryPort.save(task);
    }
}
