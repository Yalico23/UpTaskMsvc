package com.msvc_test.infrastructure.entities;

import com.msvc_test.domain.models.TypeRols;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rols")
public class RolEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Enumerated(EnumType.STRING) // Store the enum as a string in the database, otherswise it will store the ordinal (integer) value which can lead to issues if the enum order changes
    @Column(name = "type_rols", nullable = false, length = 50)
    private TypeRols typeRols;
}
