package com.msvc_test.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;
    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;
    @Column(name = "description", length = 500)
    private String description;
    @OneToMany(
            mappedBy = "project", // Specify the field in TaskEntity that owns the relationship
            cascade = CascadeType.ALL, // Ensure tasks are persisted when a project is saved
            orphanRemoval = true, // Ensure tasks are removed when a project is deleted
            fetch = FetchType.LAZY // Use LAZY fetching to avoid loading tasks when not needed
    )
    private List<TaskEntity> tasks;
}
