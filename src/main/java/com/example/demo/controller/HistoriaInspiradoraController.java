package com.example.demo.controller;

import com.example.demo.dto.HistoriaInspiradoraCreationDTO;
import com.example.demo.dto.HistoriaInspiradoraResponseDTO;
import com.example.demo.service.HistoriaInspiradoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historias-inspiradoras")
public class HistoriaInspiradoraController {

    private final HistoriaInspiradoraService historiaInspiradoraService;

    public HistoriaInspiradoraController(HistoriaInspiradoraService historiaInspiradoraService) {
        this.historiaInspiradoraService = historiaInspiradoraService;
    }

    @GetMapping
    public ResponseEntity<List<HistoriaInspiradoraResponseDTO>> getAllHistoriasInspiradoras() {
        List<HistoriaInspiradoraResponseDTO> historias = historiaInspiradoraService.findAll();
        return ResponseEntity.ok(historias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaInspiradoraResponseDTO> getHistoriaInspiradoraById(@PathVariable Long id) {
        return historiaInspiradoraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoriaInspiradoraResponseDTO> createHistoriaInspiradora(@Valid @RequestBody HistoriaInspiradoraCreationDTO dto) {
        HistoriaInspiradoraResponseDTO createdHistoria = historiaInspiradoraService.save(dto);
        return new ResponseEntity<>(createdHistoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaInspiradoraResponseDTO> updateHistoriaInspiradora(@PathVariable Long id, @Valid @RequestBody HistoriaInspiradoraCreationDTO dto) {
        try {
            HistoriaInspiradoraResponseDTO updatedHistoria = historiaInspiradoraService.update(id, dto);
            return ResponseEntity.ok(updatedHistoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaInspiradora(@PathVariable Long id) {
        historiaInspiradoraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}