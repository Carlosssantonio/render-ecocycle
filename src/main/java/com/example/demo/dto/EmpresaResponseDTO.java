package com.example.demo.dto;

import java.math.BigDecimal; // Added
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la respuesta de una empresa.
 * Contiene los campos que se envían como respuesta al cliente al consultar o crear una empresa.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class EmpresaResponseDTO {

    private Long id;
    private String nombreEmpresa;
    private String rut;
    private String nombreContacto;
    private String cargoContacto;
    private String emailCorporativo;
    private String telefono;
    private String direccion;
    // Removed capacidadMensual
    private String descripcion;
    private Instant fechaRegistro;
    private Boolean terminosAceptados; // Added
    private BigDecimal calificacion; // Added
    private Boolean verificado; // Added
    private Instant createdAt; // Added
    private Instant updatedAt; // Added
}