package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate; // Added import
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la respuesta de un registro de historial de reciclaje.
 * Contiene los campos que se envían como respuesta al cliente al consultar o crear un historial de reciclaje.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class HistorialReciclajeResponseDTO {

    private Long id;
    private Long usuarioId;
    private String usuarioNombre;
    private Long materialId;
    private String materialNombre;
    private Long empresaId;
    private String empresaNombre;
    private BigDecimal cantidadKg;
    private Integer puntosGanados;
    private LocalDate fechaReciclaje; // Changed from Instant
    private String estado;
    private BigDecimal co2Evitado; // Added
    private BigDecimal energiaAhorrada; // Added
    private BigDecimal aguaConservada; // Added
    private Instant createdAt; // Added
    private Instant updatedAt; // Added
}