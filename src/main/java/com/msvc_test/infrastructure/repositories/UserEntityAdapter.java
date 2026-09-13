package com.msvc_test.infrastructure.repositories;

import com.msvc_test.domain.models.User;
import com.msvc_test.domain.port.output.UserRepositoryPort;
import com.msvc_test.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserEntityAdapter implements UserRepositoryPort {

    private final UserEntityRepository repository;
    private final UserMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toModel(repository.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toModel);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(UUID.fromString(id))
                .map(mapper::toModel);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<User> findAll() {
        return mapper.toModel(repository.findAll());
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return repository.findByToken(token)
                .map(mapper::toModel);
    }
}
