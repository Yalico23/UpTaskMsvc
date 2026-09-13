package com.msvc_test.domain.port.input.user;

import com.msvc_test.domain.models.User;

public interface ConfirmUserTokenUseCase {
    User confirmTokenUser(String token);
    User refreshTokenUser(String email);
}
