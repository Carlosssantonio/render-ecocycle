package com.example.demo.service;

import com.example.demo.dto.DiscusionCreationDTO;
import com.example.demo.dto.DiscusionResponseDTO;
import com.example.demo.model.Discusiones;
import com.example.demo.repository.DiscusionesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscusionServiceImpl implements DiscusionService {

    private final DiscusionesRepository discusionesRepository;

    public DiscusionServiceImpl(DiscusionesRepository discusionesRepository) {
        this.discusionesRepository = discusionesRepository;
    }

    @Override
    public DiscusionResponseDTO save(DiscusionCreationDTO dto) {
        Discusiones discusion = new Discusiones();
        discusion.setIcono(dto.getIcono());
        discusion.setNombre(dto.getNombre());
        discusion.setTiempo(dto.getTiempo());
        discusion.setContenido(dto.getContenido());
        discusion.setLikes(dto.getLikes());
        discusion.setComentarios(dto.getComentarios());
        // createdAt, updatedAt are handled by @PrePersist

        Discusiones savedDiscusion = discusionesRepository.save(discusion);
        return convertToDTO(savedDiscusion);
    }

    @Override
    public Optional<DiscusionResponseDTO> findById(Long id) {
        return discusionesRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<DiscusionResponseDTO> findAll() {
        return discusionesRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiscusionResponseDTO update(Long id, DiscusionCreationDTO dto) {
        return discusionesRepository.findById(id).map(discusion -> {
            discusion.setIcono(dto.getIcono());
            discusion.setNombre(dto.getNombre());
            discusion.setTiempo(dto.getTiempo());
            discusion.setContenido(dto.getContenido());
            discusion.setLikes(dto.getLikes());
            discusion.setComentarios(dto.getComentarios());
            // updatedAt is handled by @PreUpdate

            Discusiones updatedDiscusion = discusionesRepository.save(discusion);
            return convertToDTO(updatedDiscusion);
        }).orElseThrow(() -> new RuntimeException("Discusion no encontrada con ID " + id));
    }

    @Override
    public void deleteById(Long id) {
        discusionesRepository.deleteById(id);
    }

    private DiscusionResponseDTO convertToDTO(Discusiones discusion) {
        DiscusionResponseDTO dto = new DiscusionResponseDTO();
        dto.setId(discusion.getId());
        dto.setIcono(discusion.getIcono());
        dto.setNombre(discusion.getNombre());
        dto.setTiempo(discusion.getTiempo());
        dto.setContenido(discusion.getContenido());
        dto.setLikes(discusion.getLikes());
        dto.setComentarios(discusion.getComentarios());
        dto.setCreatedAt(discusion.getCreatedAt());
        dto.setUpdatedAt(discusion.getUpdatedAt());
        return dto;
    }
}