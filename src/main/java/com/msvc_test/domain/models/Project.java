package com.msvc_test.domain.models;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private Long id;
    private String projectName;
    private String clientName;
    private String description;
    private List<Task> tasks;

    public void update(Project project){
        this.projectName = project.getProjectName();
        this.clientName = project.getClientName();
        this.description = project.getDescription();
    }
}
