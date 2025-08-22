package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogroCreationDTO {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long logroId;

    @NotNull
    private LocalDate fechaObtenido;
}