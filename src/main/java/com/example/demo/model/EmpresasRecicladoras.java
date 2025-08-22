package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "empresas_recicladoras") // Table name in snake_case
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresasRecicladoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nombre_empresa", nullable = false, length = 255)
    private String nombreEmpresa;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true, length = 255)
    private String rut;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nombre_contacto", nullable = false, length = 255)
    private String nombreContacto;

    @NotBlank
    @Size(max = 255)
    @Column(name = "cargo_contacto", nullable = false, length = 255)
    private String cargoContacto;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email_corporativo", nullable = false, unique = true, length = 255)
    private String emailCorporativo;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String telefono;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(name = "capacidad_mensual", precision = 10, scale = 2)
    private BigDecimal capacidadMensual;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @Column(name = "terminos_aceptados", nullable = false)
    private Boolean terminosAceptados;

    @Column(name = "calificacion", precision = 2, scale = 1)
    private BigDecimal calificacion;

    @Column(name = "verificado")
    private Boolean verificado;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private Instant fechaRegistro;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
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