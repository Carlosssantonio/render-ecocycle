package com.example.demo.controller;

import com.example.demo.dto.LogroCreationDTO;
import com.example.demo.dto.LogroResponseDTO;
import com.example.demo.service.LogroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logros")
public class LogroController {

    private final LogroService logroService;

    public LogroController(LogroService logroService) {
        this.logroService = logroService;
    }

    @GetMapping
    public ResponseEntity<List<LogroResponseDTO>> getAllLogros() {
        List<LogroResponseDTO> logros = logroService.findAll();
        return ResponseEntity.ok(logros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogroResponseDTO> getLogroById(@PathVariable Long id) {
        return logroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LogroResponseDTO> createLogro(@Valid @RequestBody LogroCreationDTO dto) {
        LogroResponseDTO createdLogro = logroService.save(dto);
        return new ResponseEntity<>(createdLogro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogroResponseDTO> updateLogro(@PathVariable Long id, @Valid @RequestBody LogroCreationDTO dto) {
        try {
            LogroResponseDTO updatedLogro = logroService.update(id, dto);
            return ResponseEntity.ok(updatedLogro);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogro(@PathVariable Long id) {
        logroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}