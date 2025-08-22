package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant; // Added

/**
 * Entidad que representa una recompensa en el sistema.
 * Almacena información sobre el título, descripción y los puntos necesarios para obtener la recompensa.
 */
@Entity
@Table(name = "recompensas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255) // Added length
    private String titulo;

    @NotBlank // Added based on SQL TEXT NOT NULL
    @Column(nullable = false, columnDefinition = "TEXT") // Changed from @Lob, added nullable=false
    private String descripcion;

    @NotNull
    @Column(name = "puntos_necesarios", nullable = false)
    private Integer puntosNecesarios;

    @Column(name = "created_at", nullable = false, updatable = false) // Added
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false) // Added
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