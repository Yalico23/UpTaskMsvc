package com.msvc_test.application.usecases.user;

import com.msvc_test.domain.exceptions.UserNotFoundException;
import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.input.user.ConfirmUserTokenUseCase;
import com.msvc_test.domain.port.output.EmailExternalPort;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;


import static com.msvc_test.application.usecases.utils.SecureTokens.*;
import static com.msvc_test.application.usecases.utils.SendEmail.*;

@RequiredArgsConstructor
public class ConfirmUserTokenUseCaseImpl implements ConfirmUserTokenUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final EmailExternalPort emailExternalPort;
    private final String frontendUrl;

    @Override
    public User confirmTokenUser(String token) {
        if(Objects.isNull(token)){
            throw new IllegalArgumentException("Token cannot be null");
        }
        User user = userRepositoryPort.findByToken(token).orElseThrow(()-> new UserNotFoundException("User not found with token: " + token));
        if(user.getTokenExpiration() == null || user.getTokenExpiration().before(new Date())){
            throw new IllegalArgumentException("Token has expired");
        }
        user.setToken(null);
        user.setActive(true);
        return userRepositoryPort.save(user);
    }

    @Override
    public User refreshTokenUser(String email) {
        if(Objects.isNull(email)){
            throw new IllegalArgumentException("Email cannot be null");
        }
        User user = userRepositoryPort.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found with email: " + email));
        if(user.isActive()){
            throw new IllegalArgumentException("User is already active");
        }
        user.setToken(newToken(6));
        user.setTokenExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(10))));
        sendConfirnAccount(user.getEmail(), user.getName(), user.getToken(), frontendUrl, emailExternalPort);
        return userRepositoryPort.save(user);
    }
}
