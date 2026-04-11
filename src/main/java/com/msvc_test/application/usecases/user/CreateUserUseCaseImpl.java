package com.msvc_test.application.usecases.user;

import com.msvc_test.domain.models.Rol;
import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.input.CreateUserUseCase;
import com.msvc_test.domain.port.output.RolRepositoryPort;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

import static com.msvc_test.domain.models.TypeRols.*;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        if(Objects.isNull(user)){
            throw new IllegalArgumentException("User cannot be null");
        }
        if(Objects.isNull(user.getName()) || user.getName().isEmpty()){
            throw new IllegalArgumentException("User name cannot be empty");
        }
        if(userRepositoryPort.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Rol rol = rolRepositoryPort.findByTypeRols(user.isAdmin() ? ADMIN : WORKER).orElseThrow(()-> new RuntimeException("Rol not found"));
        user.setRoles(List.of(rol));

        return userRepositoryPort.save(user);
    }
}
