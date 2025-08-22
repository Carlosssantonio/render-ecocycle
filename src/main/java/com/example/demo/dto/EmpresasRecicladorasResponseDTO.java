package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresasRecicladorasResponseDTO {

    private Long id;
    private String nombreEmpresa;
    private String rut;
    private String nombreContacto;
    private String cargoContacto;
    private String emailCorporativo;
    private String telefono;
    private String direccion;
    private BigDecimal capacidadMensual;
    private String descripcion;
    private Boolean terminosAceptados;
    private BigDecimal calificacion;
    private Boolean verificado;
    private Instant fechaRegistro;
    private Instant createdAt;
    private Instant updatedAt;
}