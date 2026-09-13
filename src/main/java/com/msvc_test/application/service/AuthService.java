package com.msvc_test.application.service;

import com.msvc_test.domain.models.Rol;
import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.input.rol.CreateRolUseCase;
import com.msvc_test.domain.port.input.user.ConfirmUserTokenUseCase;
import com.msvc_test.domain.port.input.user.CreateUserUseCase;
import com.msvc_test.domain.port.input.user.ResetUserPasswordUseCase;
import com.msvc_test.domain.port.input.user.ValidateTokenPasswordUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements CreateUserUseCase, CreateRolUseCase, ConfirmUserTokenUseCase, ResetUserPasswordUseCase, ValidateTokenPasswordUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final CreateRolUseCase createRolUseCase;
    private final ConfirmUserTokenUseCase userTokenUseCase;
    private final ResetUserPasswordUseCase userPasswordUseCase;
    private final ValidateTokenPasswordUseCase validateTokenPasswordUseCase;

    public AuthService(CreateUserUseCase createUserUseCase, CreateRolUseCase createRolUseCase, ConfirmUserTokenUseCase userTokenUseCase, ResetUserPasswordUseCase userPasswordUseCase, ValidateTokenPasswordUseCase validateTokenPasswordUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.createRolUseCase = createRolUseCase;
        this.userTokenUseCase = userTokenUseCase;
        this.userPasswordUseCase = userPasswordUseCase;
        this.validateTokenPasswordUseCase = validateTokenPasswordUseCase;
    }

    @Transactional
    @Override
    public Rol createRol(Rol rol) {
        return createRolUseCase.createRol(rol);
    }

    @Transactional
    @Override
    public User createUser(User user) {
        return createUserUseCase.createUser(user);
    }

    @Transactional
    @Override
    public User confirmTokenUser(String token) {
        return userTokenUseCase.confirmTokenUser(token);
    }

    @Transactional
    @Override
    public User refreshTokenUser(String email) {
        return userTokenUseCase.refreshTokenUser(email);
    }

    @Transactional
    @Override
    public User forgotUserPassword(String email) {
        return userPasswordUseCase.forgotUserPassword(email);
    }

    @Transactional
    @Override
    public void setNewPassword(String token, String newPassword) {
        userPasswordUseCase.setNewPassword(token,newPassword);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateTokenPassword(String token) {
        return validateTokenPasswordUseCase.validateTokenPassword(token);
    }
}
