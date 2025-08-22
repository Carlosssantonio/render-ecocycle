package com.example.demo.service;

import com.example.demo.dto.RecompensaCreationDTO;
import com.example.demo.dto.RecompensaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RecompensaService {
    RecompensaResponseDTO save(RecompensaCreationDTO dto);
    Optional<RecompensaResponseDTO> findById(Long id);
    List<RecompensaResponseDTO> findAll();
    RecompensaResponseDTO update(Long id, RecompensaCreationDTO dto);
    void deleteById(Long id);
}