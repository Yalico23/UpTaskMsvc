package com.msvc_test.application.usecases.task;

import com.msvc_test.domain.exceptions.TaskNotFound;
import com.msvc_test.domain.port.input.task.DeleteTaskUseCase;
import com.msvc_test.domain.port.output.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public void deleteTask(Long id) {
        if(Objects.isNull(id) || id <= 0){
            throw new IllegalArgumentException("Task ID must not be null or less than or equal to zero");
        }
        if(!taskRepositoryPort.existsById(id)){
            throw new TaskNotFound("Task with ID " + id + " not found");
        }
        taskRepositoryPort.deleteById(id);
    }
}
