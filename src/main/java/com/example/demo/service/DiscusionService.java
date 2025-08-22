package com.example.demo.service;

import com.example.demo.dto.DiscusionCreationDTO;
import com.example.demo.dto.DiscusionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface DiscusionService {
    DiscusionResponseDTO save(DiscusionCreationDTO dto);
    Optional<DiscusionResponseDTO> findById(Long id);
    List<DiscusionResponseDTO> findAll();
    DiscusionResponseDTO update(Long id, DiscusionCreationDTO dto);
    void deleteById(Long id);
}