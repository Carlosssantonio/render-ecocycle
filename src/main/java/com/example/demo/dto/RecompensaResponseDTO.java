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
public class RecompensaResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private Integer puntosNecesarios;
    private Instant createdAt;
    private Instant updatedAt;
}