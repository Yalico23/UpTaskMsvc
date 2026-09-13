package com.msvc_test.infrastructure.controllers;

import com.msvc_test.application.service.TaskService;
import com.msvc_test.domain.models.Task;
import com.msvc_test.domain.models.TaskStatus;
import com.msvc_test.infrastructure.dto.request.TaskDtoCreate;
import com.msvc_test.infrastructure.dto.request.TaskDtoUpdate;
import com.msvc_test.infrastructure.dto.request.TaskStatusDtoUpdate;
import com.msvc_test.infrastructure.dto.response.TaskDtoCreateResponse;
import com.msvc_test.infrastructure.dto.response.TaskDtoListResponse;
import com.msvc_test.infrastructure.mapper.TaskMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper mapper;

    @PostMapping("/project/{projectId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TaskDtoCreateResponse> createTask(@Valid @RequestBody TaskDtoCreate taskDtoCreate, @PathVariable Long projectId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(taskService.createTask(mapper.toModel(taskDtoCreate), projectId)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<TaskDtoListResponse>> getTasksByProjectId(@PathVariable Long id) {
        List<Task> taskList = taskService.listTasks(id);
        List<TaskDtoListResponse> dtoListResponses = taskList.stream().map(mapper::toListResponse).toList();
        return ResponseEntity.ok(dtoListResponses);
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TaskDtoListResponse> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.toListResponse(taskService.listTaskById(id)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TaskDtoListResponse> updateTask(@Valid @RequestBody TaskDtoUpdate update){
        return ResponseEntity.ok(mapper.toListResponse(taskService.updateTask(mapper.toModel(update))));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/taskstatus")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Task> updateTaskStatus(@Valid @RequestBody TaskStatusDtoUpdate taskStatus) {
        return ResponseEntity.ok(taskService.updateStatus(taskStatus.getId(),taskStatus.getTaskStatus()));
    }

}
