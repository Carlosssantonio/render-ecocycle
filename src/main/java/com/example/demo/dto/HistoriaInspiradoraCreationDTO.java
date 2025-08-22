package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaInspiradoraCreationDTO {

    @Size(max = 50)
    private String icono;

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @NotBlank
    private String descripcion; // TEXT in DB, so no @Size, but @NotBlank for NOT NULL

    @Size(max = 50)
    private String tiempo;
}