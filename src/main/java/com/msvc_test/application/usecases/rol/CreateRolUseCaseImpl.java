package com.msvc_test.application.usecases.rol;

import com.msvc_test.domain.models.Rol;
import com.msvc_test.domain.port.input.rol.CreateRolUseCase;
import com.msvc_test.domain.port.output.RolRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CreateRolUseCaseImpl implements CreateRolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public Rol createRol(Rol rol) {
        log.info("Creating rol: {}", rol);
        if(Objects.isNull(rol)){
            throw new IllegalArgumentException("Rol cannot be null");
        }
        if(Objects.isNull(rol.getTypeRols()) || rol.getTypeRols().toString().isEmpty()){
            throw new IllegalArgumentException("Rol type cannot be empty");
        }
        if(rolRepositoryPort.existsByTypeRols(rol.getTypeRols()) || rol.getId() != null){
            throw new IllegalArgumentException("Rol already exists");
        }
        return rolRepositoryPort.save(rol);
    }

}
