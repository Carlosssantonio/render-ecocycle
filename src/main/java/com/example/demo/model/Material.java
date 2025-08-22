package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // Added
import java.time.Instant; // Added

/**
 * Entidad que representa un material reciclable.
 * Almacena información sobre el nombre, categoría, descripción y puntos asociados al material.
 */
@Entity
@Table(name = "materiales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255) // Changed from 100
    @Column(nullable = false, length = 255) // Changed from 100
    private String nombre;

    @NotBlank
    @Size(max = 255) // Changed from 50
    @Column(nullable = false, length = 255) // Changed from 50
    private String categoria;

    @NotBlank // Added based on SQL TEXT NOT NULL
    @Column(name = "imagen", nullable = false, length = 255) // Added
    private String imagen;

    @NotBlank // Added based on SQL TEXT NOT NULL
    @Column(nullable = false, columnDefinition = "TEXT") // Removed @Lob, added columnDefinition for TEXT
    private String descripcion;

    @NotNull
    @Column(nullable = false)
    private Integer puntos;

    @Column(name = "distancia", length = 50) // Added
    private String distancia;

    @Column(name = "calificacion", precision = 2, scale = 1) // Added
    private BigDecimal calificacion;

    @Column(name = "verificado") // Added
    private Boolean verificado;

    @Column(name = "tiempo_procesamiento", length = 255) // Added
    private String tiempoProcesamiento;

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