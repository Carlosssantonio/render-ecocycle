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
public class ArticulosBlogResponseDTO {
    private Long id;
    private String titulo;
    private String fuente;
    private String descripcion;
    private String url;
    private String categoria;
    private String tipo;
    private Instant createdAt;
    private Instant updatedAt;
}
