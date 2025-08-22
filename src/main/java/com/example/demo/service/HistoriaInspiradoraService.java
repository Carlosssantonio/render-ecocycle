package com.example.demo.service;

import com.example.demo.dto.HistoriaInspiradoraCreationDTO;
import com.example.demo.dto.HistoriaInspiradoraResponseDTO;

import java.util.List;
import java.util.Optional;

public interface HistoriaInspiradoraService {
    HistoriaInspiradoraResponseDTO save(HistoriaInspiradoraCreationDTO dto);
    Optional<HistoriaInspiradoraResponseDTO> findById(Long id);
    List<HistoriaInspiradoraResponseDTO> findAll();
    HistoriaInspiradoraResponseDTO update(Long id, HistoriaInspiradoraCreationDTO dto);
    void deleteById(Long id);
}