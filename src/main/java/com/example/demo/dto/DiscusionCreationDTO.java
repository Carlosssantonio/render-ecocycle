package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscusionCreationDTO {

    @NotBlank
    @Size(max = 50)
    private String icono;

    @NotBlank
    @Size(max = 255)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String tiempo;

    @NotBlank
    private String contenido; // TEXT in DB, so no @Size, but @NotBlank for NOT NULL

    @NotNull
    private Integer likes;

    @NotNull
    private Integer comentarios;
}