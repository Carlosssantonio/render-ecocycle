package com.example.demo.service;

import com.example.demo.dto.ArticulosBlogCreationDTO;
import com.example.demo.dto.ArticulosBlogResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ArticulosBlogService {
    ArticulosBlogResponseDTO save(ArticulosBlogCreationDTO dto);
    Optional<ArticulosBlogResponseDTO> findById(Long id);
    List<ArticulosBlogResponseDTO> findAll();
    ArticulosBlogResponseDTO update(Long id, ArticulosBlogCreationDTO dto);
    void deleteById(Long id);
}