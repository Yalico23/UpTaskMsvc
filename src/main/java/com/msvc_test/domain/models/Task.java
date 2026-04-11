package com.msvc_test.domain.models;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String description;
    private Project project;
    private TaskStatus taskStatus;

    public void update(Task task){
        this.name = task.getName();
        this.description = task.getDescription();
        this.taskStatus = task.getTaskStatus();
    }
}
