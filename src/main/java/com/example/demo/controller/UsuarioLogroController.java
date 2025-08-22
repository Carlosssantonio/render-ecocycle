package com.example.demo.controller;

import com.example.demo.dto.UsuarioLogroCreationDTO;
import com.example.demo.dto.UsuarioLogroResponseDTO;
import com.example.demo.model.UsuarioLogroId;
import com.example.demo.service.UsuarioLogroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-logros")
public class UsuarioLogroController {

    private final UsuarioLogroService usuarioLogroService;

    public UsuarioLogroController(UsuarioLogroService usuarioLogroService) {
        this.usuarioLogroService = usuarioLogroService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioLogroResponseDTO>> getAllUsuarioLogros() {
        List<UsuarioLogroResponseDTO> usuarioLogros = usuarioLogroService.findAll();
        return ResponseEntity.ok(usuarioLogros);
    }

    @GetMapping("/{usuarioId}/{logroId}")
    public ResponseEntity<UsuarioLogroResponseDTO> getUsuarioLogroById(@PathVariable Long usuarioId, @PathVariable Long logroId) {
        UsuarioLogroId id = new UsuarioLogroId(usuarioId, logroId);
        return usuarioLogroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioLogroResponseDTO> createUsuarioLogro(@Valid @RequestBody UsuarioLogroCreationDTO dto) {
        UsuarioLogroResponseDTO createdUsuarioLogro = usuarioLogroService.save(dto);
        return new ResponseEntity<>(createdUsuarioLogro, HttpStatus.CREATED);
    }

    @PutMapping("/{usuarioId}/{logroId}")
    public ResponseEntity<UsuarioLogroResponseDTO> updateUsuarioLogro(@PathVariable Long usuarioId, @PathVariable Long logroId, @Valid @RequestBody UsuarioLogroCreationDTO dto) {
        try {
            UsuarioLogroId id = new UsuarioLogroId(usuarioId, logroId);
            UsuarioLogroResponseDTO updatedUsuarioLogro = usuarioLogroService.update(id, dto);
            return ResponseEntity.ok(updatedUsuarioLogro);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{usuarioId}/{logroId}")
    public ResponseEntity<Void> deleteUsuarioLogro(@PathVariable Long usuarioId, @PathVariable Long logroId) {
        UsuarioLogroId id = new UsuarioLogroId(usuarioId, logroId);
        usuarioLogroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}