package com.example.demo.service;

import com.example.demo.dto.LogroCreationDTO;
import com.example.demo.dto.LogroResponseDTO;

import java.util.List;
import java.util.Optional;

public interface LogroService {
    LogroResponseDTO save(LogroCreationDTO dto);
    Optional<LogroResponseDTO> findById(Long id);
    List<LogroResponseDTO> findAll();
    LogroResponseDTO update(Long id, LogroCreationDTO dto);
    void deleteById(Long id);
}