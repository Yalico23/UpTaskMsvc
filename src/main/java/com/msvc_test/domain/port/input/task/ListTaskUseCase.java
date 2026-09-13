package com.msvc_test.domain.port.input.task;

import com.msvc_test.domain.models.Task;

import java.util.List;

public interface ListTaskUseCase {
    List<Task> listTasks(Long projectId);
    Task listTaskById(Long id);
}
