package com.example.usuarios.dto.request;

import jakarta.validation.constraints.*;

/**
 * Record DTO (Data Transfer Object) para las solicitudes de creación y actualización de usuarios.
 * Contiene los campos necesarios para registrar o modificar la información de un usuario.
 */
public record UsuarioRequest(
        @NotBlank @Size(max = 255) String nombre, // Changed size
        @NotBlank @Size(max = 255) String apellido, // Changed size
        @NotBlank @Email @Size(max = 255) String email, // Changed size
        @NotBlank @Size(min = 8, max = 255) String password, // Contraseña del usuario (se encriptará antes de guardar)
        @NotBlank @Size(max = 255) String ciudad, // Changed size, added NotBlank
        String role, // Rol del usuario (ej. "USER", "ADMIN"), por defecto "USER"
        Integer puntosTotales, // Puntos totales acumulados por el usuario
        @Size(max = 50) String icono, // Added
        @Size(max = 50) String medalla // Added
) {}