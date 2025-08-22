package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "discusiones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discusiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String icono;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String nombre; // Consider making this a ManyToOne relationship to Usuario if it's a FK

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String tiempo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @NotNull
    @Column(nullable = false)
    private Integer likes = 0;

    @NotNull
    @Column(nullable = false)
    private Integer comentarios = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (updatedAt == null) {
            updatedAt = Instant.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}