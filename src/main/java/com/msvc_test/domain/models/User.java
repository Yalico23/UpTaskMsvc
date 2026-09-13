package com.msvc_test.domain.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Rol> roles;
    private LocalDate createdAt;
    private LocalDate updatedAt; // Solo almacena la fecha dia mes año, no la hora, por lo que no depende de la zona horaria
    private boolean admin;
    private boolean active;
    private String token;
    private Date tokenExpiration; // no depende de la zona horaria, se basa en la fecha y hora del sistema
    private List<Project> projects;
}
