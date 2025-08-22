package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "articulos_blog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticulosBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String titulo;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String fuente;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String url;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String categoria;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (updatedAt == null) {
            updatedAt = Instant.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}