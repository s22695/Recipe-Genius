package com.pro.client_service.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private Instant createdAt;

    @LastModifiedDate
    @Column(
            name = "updated_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private Instant updatedAt;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(
            name = "keycloak_id",
            nullable = false,
            unique = true,
            columnDefinition = "UUID"
    )
    private UUID keycloakId;

    @Column(nullable = false)
    private boolean enabled = true;
}
