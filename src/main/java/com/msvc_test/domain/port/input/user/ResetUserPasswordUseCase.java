package com.msvc_test.domain.port.input.user;

import com.msvc_test.domain.models.User;

public interface ResetUserPasswordUseCase {
    User forgotUserPassword(String email);
    void setNewPassword(String token, String newPassword);
}
