package com.example.demo.controller;

import com.example.demo.dto.DiscusionCreationDTO;
import com.example.demo.dto.DiscusionResponseDTO;
import com.example.demo.service.DiscusionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discusiones")
public class DiscusionController {

    private final DiscusionService discusionService;

    public DiscusionController(DiscusionService discusionService) {
        this.discusionService = discusionService;
    }

    @GetMapping
    public ResponseEntity<List<DiscusionResponseDTO>> getAllDiscusiones() {
        List<DiscusionResponseDTO> discusiones = discusionService.findAll();
        return ResponseEntity.ok(discusiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscusionResponseDTO> getDiscusionById(@PathVariable Long id) {
        return discusionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiscusionResponseDTO> createDiscusion(@Valid @RequestBody DiscusionCreationDTO dto) {
        DiscusionResponseDTO createdDiscusion = discusionService.save(dto);
        return new ResponseEntity<>(createdDiscusion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscusionResponseDTO> updateDiscusion(@PathVariable Long id, @Valid @RequestBody DiscusionCreationDTO dto) {
        try {
            DiscusionResponseDTO updatedDiscusion = discusionService.update(id, dto);
            return ResponseEntity.ok(updatedDiscusion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscusion(@PathVariable Long id) {
        discusionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}