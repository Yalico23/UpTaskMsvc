package com.msvc_test.application.usecases.task;

import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.port.input.UpdateTaskUseCase;
import com.msvc_test.domain.port.output.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task updateTask(Task task) {
        if(Objects.isNull(task)){
            throw new IllegalArgumentException("Task cannot be null");
        }

        Task existing = taskRepositoryPort.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Task with ID " + task.getId() + " not found"));

        existing.update(task);

        log.info("project reference: {}", existing.getProject());

        return taskRepositoryPort.save(existing);
    }
}
