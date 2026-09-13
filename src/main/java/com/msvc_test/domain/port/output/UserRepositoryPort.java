package com.msvc_test.domain.port.output;

import com.msvc_test.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    void deleteById(String id);
    List<User> findAll();
    boolean existsByEmail(String email);
    Optional<User> findByToken(String token);
}
