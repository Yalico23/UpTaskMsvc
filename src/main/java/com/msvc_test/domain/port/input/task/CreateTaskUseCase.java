package com.msvc_test.domain.port.input.task;

import com.msvc_test.domain.models.Task;

public interface CreateTaskUseCase {
    Task createTask(Task task, Long projectId);
}
