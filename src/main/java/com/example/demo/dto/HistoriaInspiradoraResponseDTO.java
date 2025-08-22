package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaInspiradoraResponseDTO {
    private Long id;
    private String icono;
    private String titulo;
    private String descripcion;
    private String tiempo;
    private Instant createdAt;
    private Instant updatedAt;
}