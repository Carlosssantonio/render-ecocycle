package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Added for boolean
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // Added
import java.time.Instant;

/**
 * Entidad que representa una empresa en el sistema.
 * Almacena información detallada sobre la empresa, incluyendo datos de contacto y capacidad.
 */
@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nombre_empresa", nullable = false, length = 255) // Added length
    private String nombreEmpresa;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true, length = 255) // Changed from 12
    private String rut;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nombre_contacto", nullable = false, length = 255) // Changed from 100
    private String nombreContacto;

    @NotBlank
    @Size(max = 255)
    @Column(name = "cargo_contacto", nullable = false, length = 255) // Changed from 50
    private String cargoContacto;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email_corporativo", nullable = false, unique = true, length = 255) // Added length
    private String emailCorporativo;

    @NotBlank // Added based on SQL NOT NULL
    @Size(max = 50) // Changed from 20
    @Column(nullable = false, length = 50) // Changed from 20
    private String telefono;

    @NotBlank // Added based on SQL NOT NULL
    @Size(max = 255)
    @Column(nullable = false, length = 255) // Added length
    private String direccion;

    // Removed capacidadMensual as it belongs to EmpresasRecicladoras

    @Column(columnDefinition = "TEXT") // Removed @Lob, added columnDefinition for TEXT
    private String descripcion;

    @NotNull // Added based on SQL NOT NULL
    @Column(name = "terminos_aceptados", nullable = false) // Added
    private Boolean terminosAceptados;

    @Column(name = "calificacion", precision = 2, scale = 1) // Added
    private BigDecimal calificacion;

    @Column(name = "verificado") // Added
    private Boolean verificado;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private Instant fechaRegistro;

    @Column(name = "created_at", nullable = false, updatable = false) // Added
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false) // Added
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (fechaRegistro == null) {
            fechaRegistro = Instant.now();
        }
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