package com.example.demo.controller;

import com.example.demo.dto.EmpresasRecicladorasCreationDTO;
import com.example.demo.dto.EmpresasRecicladorasResponseDTO;
import com.example.demo.service.EmpresasRecicladorasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas-recicladoras")
public class EmpresasRecicladorasController {

    private final EmpresasRecicladorasService empresasRecicladorasService;

    public EmpresasRecicladorasController(EmpresasRecicladorasService empresasRecicladorasService) {
        this.empresasRecicladorasService = empresasRecicladorasService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresasRecicladorasResponseDTO>> getAllEmpresasRecicladoras() {
        List<EmpresasRecicladorasResponseDTO> empresas = empresasRecicladorasService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresasRecicladorasResponseDTO> getEmpresasRecicladorasById(@PathVariable Long id) {
        return empresasRecicladorasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresasRecicladorasResponseDTO> createEmpresasRecicladoras(@Valid @RequestBody EmpresasRecicladorasCreationDTO dto) {
        EmpresasRecicladorasResponseDTO createdEmpresa = empresasRecicladorasService.save(dto);
        return new ResponseEntity<>(createdEmpresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasRecicladorasResponseDTO> updateEmpresasRecicladoras(@PathVariable Long id, @Valid @RequestBody EmpresasRecicladorasCreationDTO dto) {
        try {
            EmpresasRecicladorasResponseDTO updatedEmpresa = empresasRecicladorasService.update(id, dto);
            return ResponseEntity.ok(updatedEmpresa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresasRecicladoras(@PathVariable Long id) {
        empresasRecicladorasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}