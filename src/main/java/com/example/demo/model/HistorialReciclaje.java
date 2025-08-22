package com.example.demo.model;

import com.example.usuarios.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Added this import
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate; // Added

/**
 * Entidad que representa un registro en el historial de reciclaje.
 * Almacena información sobre la cantidad de material reciclado, el usuario, el material, la empresa,
 * los puntos ganados y la fecha de reciclaje.
 */
@Entity
@Table(name = "historial_reciclaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class HistorialReciclaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_recicladora_id", nullable = false) // Changed name
    private EmpresasRecicladoras empresaRecicladora; // Changed type and name

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @NotNull
    @Column(name = "cantidad_kg", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadKg;

    @NotNull
    @Column(name = "puntos_ganados", nullable = false)
    private Integer puntosGanados;

    @NotNull // Added based on SQL NOT NULL
    @Column(name = "fecha_reciclaje", nullable = false)
    private LocalDate fechaReciclaje; // Changed from Instant to LocalDate

    @NotBlank // Added based on SQL NOT NULL
    @Size(max = 50) // Changed from 255
    @Column(name = "estado", nullable = false, length = 50) // Added nullable=false, length
    private String estado;

    @Column(name = "co2_evitado", precision = 10, scale = 2) // Added
    private BigDecimal co2Evitado;

    @Column(name = "energia_ahorrada", precision = 10, scale = 2) // Added
    private BigDecimal energiaAhorrada;

    @Column(name = "agua_conservada", precision = 10, scale = 2) // Added
    private BigDecimal aguaConservada;

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