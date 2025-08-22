package com.example.demo.service;

import com.example.demo.dto.ArticulosBlogCreationDTO;
import com.example.demo.dto.ArticulosBlogResponseDTO;
import com.example.demo.model.ArticulosBlog;
import com.example.demo.repository.ArticulosBlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticulosBlogServiceImpl implements ArticulosBlogService {

    private final ArticulosBlogRepository articulosBlogRepository;

    public ArticulosBlogServiceImpl(ArticulosBlogRepository articulosBlogRepository) {
        this.articulosBlogRepository = articulosBlogRepository;
    }

    @Override
    public ArticulosBlogResponseDTO save(ArticulosBlogCreationDTO dto) {
        ArticulosBlog articulosBlog = new ArticulosBlog();
        articulosBlog.setTitulo(dto.getTitulo());
        articulosBlog.setFuente(dto.getFuente());
        articulosBlog.setDescripcion(dto.getDescripcion());
        articulosBlog.setUrl(dto.getUrl());
        articulosBlog.setCategoria(dto.getCategoria());
        articulosBlog.setTipo(dto.getTipo());
        // createdAt, updatedAt are handled by @PrePersist

        ArticulosBlog savedArticulosBlog = articulosBlogRepository.save(articulosBlog);
        return convertToDTO(savedArticulosBlog);
    }

    @Override
    public Optional<ArticulosBlogResponseDTO> findById(Long id) {
        return articulosBlogRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<ArticulosBlogResponseDTO> findAll() {
        return articulosBlogRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticulosBlogResponseDTO update(Long id, ArticulosBlogCreationDTO dto) {
        return articulosBlogRepository.findById(id).map(articulosBlog -> {
            articulosBlog.setTitulo(dto.getTitulo());
            articulosBlog.setFuente(dto.getFuente());
            articulosBlog.setDescripcion(dto.getDescripcion());
            articulosBlog.setUrl(dto.getUrl());
            articulosBlog.setCategoria(dto.getCategoria());
            articulosBlog.setTipo(dto.getTipo());
            // updatedAt is handled by @PreUpdate

            ArticulosBlog updatedArticulosBlog = articulosBlogRepository.save(articulosBlog);
            return convertToDTO(updatedArticulosBlog);
        }).orElseThrow(() -> new RuntimeException("Articulo de Blog no encontrado con ID " + id));
    }

    @Override
    public void deleteById(Long id) {
        articulosBlogRepository.deleteById(id);
    }

    private ArticulosBlogResponseDTO convertToDTO(ArticulosBlog articulosBlog) {
        ArticulosBlogResponseDTO dto = new ArticulosBlogResponseDTO();
        dto.setId(articulosBlog.getId());
        dto.setTitulo(articulosBlog.getTitulo());
        dto.setFuente(articulosBlog.getFuente());
        dto.setDescripcion(articulosBlog.getDescripcion());
        dto.setUrl(articulosBlog.getUrl());
        dto.setCategoria(articulosBlog.getCategoria());
        dto.setTipo(articulosBlog.getTipo());
        dto.setCreatedAt(articulosBlog.getCreatedAt());
        dto.setUpdatedAt(articulosBlog.getUpdatedAt());
        return dto;
    }
}