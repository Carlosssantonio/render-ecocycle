package com.example.demo.controller;

import com.example.demo.dto.ArticulosBlogCreationDTO;
import com.example.demo.dto.ArticulosBlogResponseDTO;
import com.example.demo.service.ArticulosBlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos-blog")
public class ArticulosBlogController {

    private final ArticulosBlogService articulosBlogService;

    public ArticulosBlogController(ArticulosBlogService articulosBlogService) {
        this.articulosBlogService = articulosBlogService;
    }

    @GetMapping
    public ResponseEntity<List<ArticulosBlogResponseDTO>> getAllArticulosBlog() {
        List<ArticulosBlogResponseDTO> articulos = articulosBlogService.findAll();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticulosBlogResponseDTO> getArticulosBlogById(@PathVariable Long id) {
        return articulosBlogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArticulosBlogResponseDTO> createArticulosBlog(@Valid @RequestBody ArticulosBlogCreationDTO dto) {
        ArticulosBlogResponseDTO createdArticulo = articulosBlogService.save(dto);
        return new ResponseEntity<>(createdArticulo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticulosBlogResponseDTO> updateArticulosBlog(@PathVariable Long id, @Valid @RequestBody ArticulosBlogCreationDTO dto) {
        try {
            ArticulosBlogResponseDTO updatedArticulo = articulosBlogService.update(id, dto);
            return ResponseEntity.ok(updatedArticulo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticulosBlog(@PathVariable Long id) {
        articulosBlogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}