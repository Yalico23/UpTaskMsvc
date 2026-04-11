package com.msvc_test.infrastructure.controllers;

import com.msvc_test.application.service.ProjectService;
import com.msvc_test.domain.models.Project;
import com.msvc_test.infrastructure.dto.request.ProjectDtoCreate;
import com.msvc_test.infrastructure.dto.request.ProjectDtoUpdate;
import com.msvc_test.infrastructure.mapper.ProjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<Project> responseEntity(@Valid @RequestBody ProjectDtoCreate project){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectMapper.toModel(project)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Project>> listProjects(){
        return ResponseEntity.ok(projectService.listProjects());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Project> listProjects(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping()
    public ResponseEntity<Project> responseEntity(@Valid @RequestBody ProjectDtoUpdate project){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(projectMapper.toModel(project)));
    }

}
