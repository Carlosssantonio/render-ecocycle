package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Added for Boolean
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // Added

/**
 * DTO (Data Transfer Object) para la creación de una nueva empresa.
 * Contiene los campos necesarios para registrar una empresa en el sistema.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // This will need to be regenerated or manually updated if fields are added
public class EmpresaCreationDTO {

    @NotBlank
    @Size(max = 255)
    private String nombreEmpresa;

    @NotBlank
    @Size(max = 255) // Changed from 12
    private String rut;

    @NotBlank
    @Size(max = 255) // Changed from 100
    private String nombreContacto;

    @NotBlank
    @Size(max = 255) // Changed from 50
    private String cargoContacto;

    @NotBlank
    @Email
    @Size(max = 255)
    private String emailCorporativo;

    @NotBlank // Added
    @Size(max = 50) // Changed from 20
    private String telefono;

    @NotBlank // Added
    @Size(max = 255)
    private String direccion;

    // Removed capacidadMensual

    private String descripcion; // No @NotBlank as it's TEXT and can be null in SQL

    @NotNull // Added based on SQL NOT NULL
    private Boolean terminosAceptados; // Added

    private BigDecimal calificacion; // Added

    private Boolean verificado; // Added
}