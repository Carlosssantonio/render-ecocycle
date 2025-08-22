package com.example.demo.service;

import com.example.demo.dto.LogroCreationDTO;
import com.example.demo.dto.LogroResponseDTO;
import com.example.demo.model.Logro;
import com.example.demo.repository.LogroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogroServiceImpl implements LogroService {

    private final LogroRepository logroRepository;

    public LogroServiceImpl(LogroRepository logroRepository) {
        this.logroRepository = logroRepository;
    }

    @Override
    public LogroResponseDTO save(LogroCreationDTO dto) {
        Logro logro = new Logro();
        logro.setTitulo(dto.getTitulo());
        logro.setDescripcion(dto.getDescripcion());
        // createdAt and updatedAt are handled by @PrePersist

        Logro savedLogro = logroRepository.save(logro);
        return convertToDTO(savedLogro);
    }

    @Override
    public Optional<LogroResponseDTO> findById(Long id) {
        return logroRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<LogroResponseDTO> findAll() {
        return logroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LogroResponseDTO update(Long id, LogroCreationDTO dto) {
        return logroRepository.findById(id).map(logro -> {
            logro.setTitulo(dto.getTitulo());
            logro.setDescripcion(dto.getDescripcion());
            // updatedAt is handled by @PreUpdate

            Logro updatedLogro = logroRepository.save(logro);
            return convertToDTO(updatedLogro);
        }).orElseThrow(() -> new RuntimeException("Logro no encontrado con ID " + id));
    }

    @Override
    public void deleteById(Long id) {
        logroRepository.deleteById(id);
    }

    private LogroResponseDTO convertToDTO(Logro logro) {
        LogroResponseDTO dto = new LogroResponseDTO();
        dto.setId(logro.getId());
        dto.setTitulo(logro.getTitulo());
        dto.setDescripcion(logro.getDescripcion());
        dto.setCreatedAt(logro.getCreatedAt());
        dto.setUpdatedAt(logro.getUpdatedAt());
        return dto;
    }
}
