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
import com.msvc_test.application.usecases.user.ConfirmUserTokenUseCaseImpl;
import com.msvc_test.application.usecases.user.CreateUserUseCaseImpl;
import com.msvc_test.application.usecases.user.ResetUserPasswordUseCaseImpl;
import com.msvc_test.application.usecases.user.ValidateTokenPasswordUseCaseImpl;
import com.msvc_test.domain.port.output.*;
import com.msvc_test.infrastructure.adapaters.EmailExternalAdapter;
import com.msvc_test.infrastructure.configuration.auth.utils.SecurityUtils;
import com.msvc_test.infrastructure.repositories.ProjectEntityAdapter;
import com.msvc_test.infrastructure.repositories.RolEntityAdapter;
import com.msvc_test.infrastructure.repositories.TaskEntityAdapter;
import com.msvc_test.infrastructure.repositories.UserEntityAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class ApplicationConfig {

    @Bean
    public AuthService authService(RolRepositoryPort rolRepositoryPort, UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder, EmailExternalPort emailExternalPort, @Value("${frontend.urlTest}") String urlFrontend) {

        return new AuthService(
                new CreateUserUseCaseImpl(userRepositoryPort,rolRepositoryPort,passwordEncoder,emailExternalPort,urlFrontend),
                new CreateRolUseCaseImpl(rolRepositoryPort),
                new ConfirmUserTokenUseCaseImpl(userRepositoryPort,emailExternalPort,urlFrontend),
                new ResetUserPasswordUseCaseImpl(userRepositoryPort,emailExternalPort,urlFrontend,passwordEncoder),
                new ValidateTokenPasswordUseCaseImpl(userRepositoryPort));
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
    public ProjectService projectService(ProjectRepositoryPort projectRepositoryPort, SecurityUtils securityUtils){
        return new ProjectService(new CreateProjectUseCaseImpl(projectRepositoryPort, securityUtils),
                new ListProjectsUseCaseImpl(projectRepositoryPort,securityUtils),
                new DeleteProjectUseCaseImpl(projectRepositoryPort,securityUtils),
                new UpdateProjectUseCaseImpl(projectRepositoryPort,securityUtils));
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

    @Bean
    public EmailExternalPort emailExternalPort(JavaMailSender javaMailSender){
        return new EmailExternalAdapter(javaMailSender);
    }

    @Bean
    public JavaMailSender javaMailSender(
            @Value("${spring.mail.host}") String host,
            @Value("${spring.mail.port}") int port,
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password,
            @Value("${spring.mail.protocol:smtp}") String protocol)
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);
        props.put("mail.smtp.timeout", 10000);
        props.put("mail.smtp.writetimeout", 10000);
        // Para debugging (puedes remover después)
        //props.put("mail.debug", "true");

        return mailSender;
    }
}
