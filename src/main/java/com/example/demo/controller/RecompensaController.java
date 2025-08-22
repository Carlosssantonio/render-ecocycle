package com.example.demo.controller;

import com.example.demo.dto.RecompensaCreationDTO;
import com.example.demo.dto.RecompensaResponseDTO;
import com.example.demo.service.RecompensaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recompensas")
public class RecompensaController {

    private final RecompensaService recompensaService;

    public RecompensaController(RecompensaService recompensaService) {
        this.recompensaService = recompensaService;
    }

    @GetMapping
    public ResponseEntity<List<RecompensaResponseDTO>> getAllRecompensas() {
        List<RecompensaResponseDTO> recompensas = recompensaService.findAll();
        return ResponseEntity.ok(recompensas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecompensaResponseDTO> getRecompensaById(@PathVariable Long id) {
        return recompensaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecompensaResponseDTO> createRecompensa(@Valid @RequestBody RecompensaCreationDTO dto) {
        RecompensaResponseDTO createdRecompensa = recompensaService.save(dto);
        return new ResponseEntity<>(createdRecompensa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecompensaResponseDTO> updateRecompensa(@PathVariable Long id, @Valid @RequestBody RecompensaCreationDTO dto) {
        try {
            RecompensaResponseDTO updatedRecompensa = recompensaService.update(id, dto);
            return ResponseEntity.ok(updatedRecompensa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecompensa(@PathVariable Long id) {
        recompensaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}