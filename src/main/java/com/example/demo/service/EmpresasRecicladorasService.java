package com.example.demo.service;

import com.example.demo.dto.EmpresasRecicladorasCreationDTO;
import com.example.demo.dto.EmpresasRecicladorasResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EmpresasRecicladorasService {
    EmpresasRecicladorasResponseDTO save(EmpresasRecicladorasCreationDTO dto);
    Optional<EmpresasRecicladorasResponseDTO> findById(Long id);
    List<EmpresasRecicladorasResponseDTO> findAll();
    EmpresasRecicladorasResponseDTO update(Long id, EmpresasRecicladorasCreationDTO dto);
    void deleteById(Long id);
}