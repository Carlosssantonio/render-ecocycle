package com.example.demo.service;

import com.example.demo.dto.RecompensaCreationDTO;
import com.example.demo.dto.RecompensaResponseDTO;
import com.example.demo.model.Recompensa;
import com.example.demo.repository.RecompensaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecompensaServiceImpl implements RecompensaService {

    private final RecompensaRepository recompensaRepository;

    public RecompensaServiceImpl(RecompensaRepository recompensaRepository) {
        this.recompensaRepository = recompensaRepository;
    }

    @Override
    public RecompensaResponseDTO save(RecompensaCreationDTO dto) {
        Recompensa recompensa = new Recompensa();
        recompensa.setTitulo(dto.getTitulo());
        recompensa.setDescripcion(dto.getDescripcion());
        recompensa.setPuntosNecesarios(dto.getPuntosNecesarios());
        // createdAt, updatedAt are handled by @PrePersist

        Recompensa savedRecompensa = recompensaRepository.save(recompensa);
        return convertToDTO(savedRecompensa);
    }

    @Override
    public Optional<RecompensaResponseDTO> findById(Long id) {
        return recompensaRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<RecompensaResponseDTO> findAll() {
        return recompensaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecompensaResponseDTO update(Long id, RecompensaCreationDTO dto) {
        return recompensaRepository.findById(id).map(recompensa -> {
            recompensa.setTitulo(dto.getTitulo());
            recompensa.setDescripcion(dto.getDescripcion());
            recompensa.setPuntosNecesarios(dto.getPuntosNecesarios());
            // updatedAt is handled by @PreUpdate

            Recompensa updatedRecompensa = recompensaRepository.save(recompensa);
            return convertToDTO(updatedRecompensa);
        }).orElseThrow(() -> new RuntimeException("Recompensa no encontrada con ID " + id));
    }

    @Override
    public void deleteById(Long id) {
        recompensaRepository.deleteById(id);
    }

    private RecompensaResponseDTO convertToDTO(Recompensa recompensa) {
        RecompensaResponseDTO dto = new RecompensaResponseDTO();
        dto.setId(recompensa.getId());
        dto.setTitulo(recompensa.getTitulo());
        dto.setDescripcion(recompensa.getDescripcion());
        dto.setPuntosNecesarios(recompensa.getPuntosNecesarios());
        dto.setCreatedAt(recompensa.getCreatedAt());
        dto.setUpdatedAt(recompensa.getUpdatedAt());
        return dto;
    }
}