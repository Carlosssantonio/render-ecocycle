package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresasRecicladorasCreationDTO {

    @NotBlank
    @Size(max = 255)
    private String nombreEmpresa;

    @NotBlank
    @Size(max = 255)
    private String rut;

    @NotBlank
    @Size(max = 255)
    private String nombreContacto;

    @NotBlank
    @Size(max = 255)
    private String cargoContacto;

    @NotBlank
    @Email
    @Size(max = 255)
    private String emailCorporativo;

    @NotBlank
    @Size(max = 50)
    private String telefono;

    @NotBlank
    @Size(max = 255)
    private String direccion;

    private BigDecimal capacidadMensual; // Can be null in DB

    private String descripcion; // Can be null in DB

    @NotNull
    private Boolean terminosAceptados;

    private BigDecimal calificacion; // Can be null in DB

    private Boolean verificado; // Can be null in DB
}