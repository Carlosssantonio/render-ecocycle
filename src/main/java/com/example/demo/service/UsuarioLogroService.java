package com.example.demo.service;

import com.example.demo.dto.UsuarioLogroCreationDTO;
import com.example.demo.dto.UsuarioLogroResponseDTO;
import com.example.demo.model.UsuarioLogroId; // Import the composite key

import java.util.List;
import java.util.Optional;

public interface UsuarioLogroService {
    UsuarioLogroResponseDTO save(UsuarioLogroCreationDTO dto);
    Optional<UsuarioLogroResponseDTO> findById(UsuarioLogroId id); // Use composite key
    List<UsuarioLogroResponseDTO> findAll();
    UsuarioLogroResponseDTO update(UsuarioLogroId id, UsuarioLogroCreationDTO dto); // Use composite key
    void deleteById(UsuarioLogroId id); // Use composite key
}