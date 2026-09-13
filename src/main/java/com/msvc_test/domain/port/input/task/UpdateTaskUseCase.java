package com.msvc_test.domain.port.input.task;

import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.models.TaskStatus;

public interface UpdateTaskUseCase {
    Task updateTask(Task task);
    Task updateStatus(Long id, TaskStatus status);
}
