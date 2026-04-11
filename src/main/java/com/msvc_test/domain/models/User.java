package com.msvc_test.domain.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private boolean admin;

}
