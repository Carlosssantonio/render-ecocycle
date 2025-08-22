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
public class DiscusionResponseDTO {
    private Long id;
    private String icono;
    private String nombre;
    private String tiempo;
    private String contenido;
    private Integer likes;
    private Integer comentarios;
    private Instant createdAt;
    private Instant updatedAt;
}
