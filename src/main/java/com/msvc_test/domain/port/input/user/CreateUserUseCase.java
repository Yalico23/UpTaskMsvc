package com.msvc_test.domain.port.input.user;

import com.msvc_test.domain.models.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
