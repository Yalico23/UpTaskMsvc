package com.msvc_test.infrastructure.controllers;

import com.msvc_test.application.service.AuthService;
import com.msvc_test.domain.models.User;
import com.msvc_test.infrastructure.dto.request.UserDtoRequestToken;
import com.msvc_test.infrastructure.dto.request.UserDtoSetNewPassword;
import com.msvc_test.infrastructure.dto.request.UserDtoValidateToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUser(user));
    }

    @PutMapping("/confirm/{token}")
    public ResponseEntity<User> confirmUserToken(@PathVariable String token) {
        return ResponseEntity.ok(authService.confirmTokenUser(token));
    }

    @PutMapping("/refresh-token")
    public ResponseEntity<User> refreshUserToken(@Valid @RequestBody UserDtoRequestToken email) {
        return ResponseEntity.ok(authService.refreshTokenUser(email.getEmail()));
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<User> forgotPassword(@Valid @RequestBody UserDtoRequestToken email) {
        return ResponseEntity.ok(authService.forgotUserPassword(email.getEmail()));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateTokenPassword(@Valid @RequestBody UserDtoValidateToken token) {
        return ResponseEntity.ok(authService.validateTokenPassword(token.getToken()));
    }

    @PutMapping("/set-new-password")
    public ResponseEntity<Void> setNewPassword(@Valid @RequestBody UserDtoSetNewPassword userDtoSetNewPassword) {
        authService.setNewPassword(userDtoSetNewPassword.getToken(),userDtoSetNewPassword.getPassword());
        return ResponseEntity.noContent().build();
    }
}
