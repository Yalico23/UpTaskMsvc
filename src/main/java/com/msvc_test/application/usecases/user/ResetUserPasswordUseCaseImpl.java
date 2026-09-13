package com.msvc_test.application.usecases.user;

import com.msvc_test.domain.exceptions.UserNotFoundException;
import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.input.user.ResetUserPasswordUseCase;
import com.msvc_test.domain.port.output.EmailExternalPort;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import static com.msvc_test.application.usecases.utils.SecureTokens.*;
import static com.msvc_test.application.usecases.utils.SendEmail.*;

@RequiredArgsConstructor
public class ResetUserPasswordUseCaseImpl implements ResetUserPasswordUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final EmailExternalPort emailExternalPort;
    private final String frontendUrl;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User forgotUserPassword(String email) {

        if(Objects.isNull(email)){
            throw new IllegalArgumentException("Email is required");
        }
        User user = userRepositoryPort.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found with email: " + email));
        user.setToken(newToken(6));
        user.setTokenExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(10))));
        sendResetPassword(user.getEmail(),user.getName(),user.getToken(),frontendUrl, emailExternalPort);
        return userRepositoryPort.save(user);
    }

    @Override
    public void setNewPassword(String token, String newPassword) {
        if(Objects.isNull(token) || Objects.isNull(newPassword)){
            throw new IllegalArgumentException("Token and new password are required");
        }
        User user = userRepositoryPort.findByToken(token).orElseThrow(()-> new UserNotFoundException("User not found with token: " + token));
        if(user.getTokenExpiration().before(new Date())) {
            throw new IllegalArgumentException("Token has expired");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setToken(null);
        userRepositoryPort.save(user);
    }
}
