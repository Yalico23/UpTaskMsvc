package com.msvc_test.application.usecases.user;

import com.msvc_test.domain.models.Rol;
import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.input.user.CreateUserUseCase;
import com.msvc_test.domain.port.output.EmailExternalPort;
import com.msvc_test.domain.port.output.RolRepositoryPort;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.msvc_test.domain.models.TypeRols.*;
import static com.msvc_test.application.usecases.utils.SendEmail.*;
import static com.msvc_test.application.usecases.utils.SecureTokens.*;

@Slf4j
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final EmailExternalPort emailExternalPort;
    private final String frontendUrl;

    @Override
    public User createUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (Objects.isNull(user.getName()) || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Rol rol = rolRepositoryPort.findByTypeRols(user.isAdmin() ? ADMIN : WORKER).orElseThrow(() -> new RuntimeException("Rol not found"));
        user.setRoles(List.of(rol));
        user.setCreatedAt(LocalDate.now());
        user.setToken(newToken(6));
        user.setTokenExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(10))));

        sendConfirnAccount(user.getEmail(), user.getName(), user.getToken(), frontendUrl, emailExternalPort);

        return userRepositoryPort.save(user);
    }




}
