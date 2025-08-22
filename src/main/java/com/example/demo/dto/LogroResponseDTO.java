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
public class LogroResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private Instant createdAt;
    private Instant updatedAt;
}