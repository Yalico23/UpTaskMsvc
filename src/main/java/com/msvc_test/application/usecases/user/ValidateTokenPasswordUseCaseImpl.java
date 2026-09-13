package com.msvc_test.application.usecases.user;

import com.msvc_test.domain.exceptions.UserNotFoundException;
import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.input.user.ValidateTokenPasswordUseCase;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
public class ValidateTokenPasswordUseCaseImpl implements ValidateTokenPasswordUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public boolean validateTokenPassword(String token) {
        if(Objects.isNull(token)){
            throw new IllegalArgumentException("Token cannot be null");
        }
        User user = userRepositoryPort.findByToken(token).orElseThrow(()-> new UserNotFoundException("User not found with token: " + token));
        if (user.getTokenExpiration() == null) {
            throw new IllegalStateException("Token expiration missing");
        }

        if (user.getTokenExpiration().before(new Date())) {
            throw new RuntimeException("Token expired");
        }
        return true;
    }
}
