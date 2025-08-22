package com.example.demo.service;

import com.example.demo.dto.HistoriaInspiradoraCreationDTO;
import com.example.demo.dto.HistoriaInspiradoraResponseDTO;
import com.example.demo.model.HistoriasInspiradoras;
import com.example.demo.repository.HistoriasInspiradorasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoriaInspiradoraServiceImpl implements HistoriaInspiradoraService {

    private final HistoriasInspiradorasRepository historiasInspiradorasRepository;

    public HistoriaInspiradoraServiceImpl(HistoriasInspiradorasRepository historiasInspiradorasRepository) {
        this.historiasInspiradorasRepository = historiasInspiradorasRepository;
    }

    @Override
    public HistoriaInspiradoraResponseDTO save(HistoriaInspiradoraCreationDTO dto) {
        HistoriasInspiradoras historia = new HistoriasInspiradoras();
        historia.setIcono(dto.getIcono());
        historia.setTitulo(dto.getTitulo());
        historia.setDescripcion(dto.getDescripcion());
        historia.setTiempo(dto.getTiempo());
        // createdAt, updatedAt are handled by @PrePersist

        HistoriasInspiradoras savedHistoria = historiasInspiradorasRepository.save(historia);
        return convertToDTO(savedHistoria);
    }

    @Override
    public Optional<HistoriaInspiradoraResponseDTO> findById(Long id) {
        return historiasInspiradorasRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<HistoriaInspiradoraResponseDTO> findAll() {
        return historiasInspiradorasRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoriaInspiradoraResponseDTO update(Long id, HistoriaInspiradoraCreationDTO dto) {
        return historiasInspiradorasRepository.findById(id).map(historia -> {
            historia.setIcono(dto.getIcono());
            historia.setTitulo(dto.getTitulo());
            historia.setDescripcion(dto.getDescripcion());
            historia.setTiempo(dto.getTiempo());
            // updatedAt is handled by @PreUpdate

            HistoriasInspiradoras updatedHistoria = historiasInspiradorasRepository.save(historia);
            return convertToDTO(updatedHistoria);
        }).orElseThrow(() -> new RuntimeException("Historia Inspiradora no encontrada con ID " + id));
    }

    @Override
    public void deleteById(Long id) {
        historiasInspiradorasRepository.deleteById(id);
    }

    private HistoriaInspiradoraResponseDTO convertToDTO(HistoriasInspiradoras historia) {
        HistoriaInspiradoraResponseDTO dto = new HistoriaInspiradoraResponseDTO();
        dto.setId(historia.getId());
        dto.setIcono(historia.getIcono());
        dto.setTitulo(historia.getTitulo());
        dto.setDescripcion(historia.getDescripcion());
        dto.setTiempo(historia.getTiempo());
        dto.setCreatedAt(historia.getCreatedAt());
        dto.setUpdatedAt(historia.getUpdatedAt());
        return dto;
    }
}