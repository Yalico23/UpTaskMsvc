package com.msvc_test.application.service;

import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.port.input.CreateTaskUseCase;
import com.msvc_test.domain.port.input.DeleteTaskUseCase;
import com.msvc_test.domain.port.input.ListTaskUseCase;
import com.msvc_test.domain.port.input.UpdateTaskUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService implements CreateTaskUseCase, ListTaskUseCase, UpdateTaskUseCase, DeleteTaskUseCase {

    private final CreateTaskUseCase createTaskUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskService(CreateTaskUseCase createTaskUseCase, ListTaskUseCase listTaskUseCase, UpdateTaskUseCase updateTaskUseCase, DeleteTaskUseCase deleteTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.listTaskUseCase = listTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }

    @Transactional
    @Override
    public Task createTask(Task task, Long projectId) {
        return createTaskUseCase.createTask(task, projectId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> listTasks(Long projectId) {
        return listTaskUseCase.listTasks(projectId);
    }

    @Transactional(readOnly = true)
    @Override
    public Task listTaskById(Long id) {
        return listTaskUseCase.listTaskById(id);
    }

    @Transactional
    @Override
    public Task updateTask(Task task) {
        return updateTaskUseCase.updateTask(task);
    }

    @Transactional
    @Override
    public void deleteTask(Long id) {
        deleteTaskUseCase.deleteTask(id);
    }
}
