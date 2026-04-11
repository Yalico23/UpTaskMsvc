package com.msvc_test.domain.port.input;

import com.msvc_test.domain.models.Task;

public interface UpdateTaskUseCase {
    Task updateTask(Task task);
}
