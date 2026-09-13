package com.msvc_test.infrastructure.repositories;

import com.msvc_test.infrastructure.configuration.auth.CustomUserDetails;
import com.msvc_test.infrastructure.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsRepository implements UserDetailsService {

    private final UserEntityRepository repository;

    public UserDetailsRepository(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + username));
        List<GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getTypeRols().name()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isActive(),
                true,
                true,
                true,
                authorities,
                userEntity.getId());
    }
}
