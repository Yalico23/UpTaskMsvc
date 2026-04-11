package com.msvc_test.infrastructure.configuration;

import com.msvc_test.application.service.AuthService;
import com.msvc_test.application.service.ProjectService;
import com.msvc_test.application.service.TaskService;
import com.msvc_test.application.usecases.project.CreateProjectUseCaseImpl;
import com.msvc_test.application.usecases.project.DeleteProjectUseCaseImpl;
import com.msvc_test.application.usecases.project.ListProjectsUseCaseImpl;
import com.msvc_test.application.usecases.project.UpdateProjectUseCaseImpl;
import com.msvc_test.application.usecases.rol.CreateRolUseCaseImpl;
import com.msvc_test.application.usecases.task.CreateTaskUseCaseImpl;
import com.msvc_test.application.usecases.task.DeleteTaskUseCaseImpl;
import com.msvc_test.application.usecases.task.ListTaskUseCaseImpl;
import com.msvc_test.application.usecases.task.UpdateTaskUseCaseImpl;
import com.msvc_test.application.usecases.user.CreateUserUseCaseImpl;
import com.msvc_test.domain.port.output.ProjectRepositoryPort;
import com.msvc_test.domain.port.output.RolRepositoryPort;
import com.msvc_test.domain.port.output.TaskRepositoryPort;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import com.msvc_test.infrastructure.repositories.ProjectEntityAdapter;
import com.msvc_test.infrastructure.repositories.RolEntityAdapter;
import com.msvc_test.infrastructure.repositories.TaskEntityAdapter;
import com.msvc_test.infrastructure.repositories.UserEntityAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public AuthService authService(RolRepositoryPort rolRepositoryPort, UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {

        return new AuthService(
                new CreateUserUseCaseImpl(userRepositoryPort,rolRepositoryPort,passwordEncoder),
                new CreateRolUseCaseImpl(rolRepositoryPort));
    }

    @Bean
    public RolRepositoryPort rolRepositoryPort(RolEntityAdapter rolRepositoryAdapter) {
        return rolRepositoryAdapter;
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserEntityAdapter userEntityAdapter) {
        return userEntityAdapter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ProjectService projectService(ProjectRepositoryPort projectRepositoryPort){
        return new ProjectService(new CreateProjectUseCaseImpl(projectRepositoryPort),
                new ListProjectsUseCaseImpl(projectRepositoryPort),
                new DeleteProjectUseCaseImpl(projectRepositoryPort),
                new UpdateProjectUseCaseImpl(projectRepositoryPort));
    }

    @Bean
    public ProjectRepositoryPort projectRepositoryPort(ProjectEntityAdapter projectEntityAdapter){
        return projectEntityAdapter;
    }

    @Bean
    public TaskService taskService(TaskRepositoryPort taskRepositoryPort, ProjectRepositoryPort projectRepositoryPort){
        return new TaskService(
                new CreateTaskUseCaseImpl(taskRepositoryPort,projectRepositoryPort),
                new ListTaskUseCaseImpl(taskRepositoryPort,projectRepositoryPort),
                new UpdateTaskUseCaseImpl(taskRepositoryPort),
                new DeleteTaskUseCaseImpl(taskRepositoryPort));
    }

    @Bean
    public TaskRepositoryPort taskRepositoryPort(TaskEntityAdapter taskEntityAdapter){
        return taskEntityAdapter;
    }
}
