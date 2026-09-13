package com.msvc_test.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<RolEntity> roles;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    private boolean active;
    @Column(name = "token", length = 6)
    private String token; // en póstgre seria
    @Column(name = "token_expiration")
    private Date tokenExpiration; // en postgre seria timestamp
    @OneToMany(
            mappedBy = "user", // Specify the field in ProjectEntity that owns the relationship
            cascade = CascadeType.ALL, // Ensure projects are persisted when a user is saved
            orphanRemoval = true, // Ensure projects are removed when a user is deleted
            fetch = FetchType.LAZY // Use LAZY fetching to avoid loading projects when not needed
    )
    private List<ProjectEntity> projects;

    @PrePersist
    public void prePersist() {
        this.updatedAt = LocalDate.now();
    }
}