package com.example.demo.service;

import com.example.demo.dto.HistorialReciclajeCreationDTO;
import com.example.demo.dto.HistorialReciclajeResponseDTO;
import com.example.demo.model.EmpresasRecicladoras; // Changed import
import com.example.demo.model.HistorialReciclaje;
import com.example.demo.model.Material;
import com.example.usuarios.model.Usuario;
import com.example.demo.repository.EmpresaRepository; // Keep this if still used elsewhere, but for HistorialReciclajeService, it's EmpresasRecicladorasRepository
import com.example.demo.repository.EmpresasRecicladorasRepository; // Added import
import com.example.demo.repository.HistorialReciclajeRepository;
import com.example.demo.repository.MaterialRepository;
import com.example.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate; // Added import
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión del historial de reciclaje.
 * Proporciona la lógica de negocio para operaciones CRUD sobre los registros de reciclaje,
 * incluyendo la actualización de puntos del usuario.
 */
@Service
public class HistorialReciclajeService {

    private final HistorialReciclajeRepository historialReciclajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final MaterialRepository materialRepository;
    private final EmpresasRecicladorasRepository empresasRecicladorasRepository; // Changed type and name

    public HistorialReciclajeService(HistorialReciclajeRepository historialReciclajeRepository,
                                     UsuarioRepository usuarioRepository,
                                     MaterialRepository materialRepository,
                                     EmpresasRecicladorasRepository empresasRecicladorasRepository) { // Changed parameter
        this.historialReciclajeRepository = historialReciclajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.materialRepository = materialRepository;
        this.empresasRecicladorasRepository = empresasRecicladorasRepository; // Changed assignment
    }

    public HistorialReciclajeResponseDTO save(HistorialReciclajeCreationDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Material material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        EmpresasRecicladoras empresaRecicladora = empresasRecicladorasRepository.findById(dto.getEmpresaId()) // Changed type and variable name
                .orElseThrow(() -> new RuntimeException("Empresa Recicladora no encontrada")); // Changed message

        HistorialReciclaje historial = new HistorialReciclaje();
        historial.setUsuario(usuario);
        historial.setMaterial(material);
        historial.setEmpresaRecicladora(empresaRecicladora); // Changed method call
        historial.setCantidadKg(dto.getCantidadKg());
        // Calcular puntos ganados (ejemplo: 10 puntos por kg)
        historial.setPuntosGanados(dto.getCantidadKg().multiply(new java.math.BigDecimal(10)).intValue());
        historial.setFechaReciclaje(LocalDate.now()); // Changed to LocalDate.now()
        historial.setEstado(dto.getEstado() != null ? dto.getEstado() : "Completado");

        HistorialReciclaje savedHistorial = historialReciclajeRepository.save(historial);

        // Actualizar puntos totales del usuario
        usuario.setPuntosTotales(usuario.getPuntosTotales() + savedHistorial.getPuntosGanados());
        usuarioRepository.save(usuario);

        return convertToDTO(savedHistorial);
    }

    public Optional<HistorialReciclajeResponseDTO> findById(Long id) {
        return historialReciclajeRepository.findById(id).map(this::convertToDTO);
    }

    public List<HistorialReciclajeResponseDTO> findAll() {
        return historialReciclajeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HistorialReciclajeResponseDTO update(Long id, HistorialReciclajeCreationDTO dto) {
        return historialReciclajeRepository.findById(id).map(historial -> {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Material material = materialRepository.findById(dto.getMaterialId())
                    .orElseThrow(() -> new RuntimeException("Material no encontrado"));
            EmpresasRecicladoras empresaRecicladora = empresasRecicladorasRepository.findById(dto.getEmpresaId()) // Changed type and variable name
                    .orElseThrow(() -> new RuntimeException("Empresa Recicladora no encontrada")); // Changed message

            historial.setUsuario(usuario);
            historial.setMaterial(material);
            historial.setEmpresaRecicladora(empresaRecicladora); // Changed method call
            historial.setCantidadKg(dto.getCantidadKg());
            historial.setPuntosGanados(dto.getCantidadKg().multiply(new java.math.BigDecimal(10)).intValue()); // Recalcular puntos
            historial.setEstado(dto.getEstado() != null ? dto.getEstado() : historial.getEstado());

            return convertToDTO(historialReciclajeRepository.save(historial));
        }).orElseThrow(() -> new RuntimeException("Historial de Reciclaje no encontrado con ID " + id));
    }

    public void deleteById(Long id) {
        historialReciclajeRepository.deleteById(id);
    }

    private HistorialReciclajeResponseDTO convertToDTO(HistorialReciclaje historial) {
        HistorialReciclajeResponseDTO dto = new HistorialReciclajeResponseDTO();
        dto.setId(historial.getId());
        dto.setUsuarioId(historial.getUsuario().getId());
        dto.setUsuarioNombre(historial.getUsuario().getNombre() + " " + historial.getUsuario().getApellido());
        dto.setMaterialId(historial.getMaterial().getId());
        dto.setMaterialNombre(historial.getMaterial().getNombre());
        dto.setEmpresaId(historial.getEmpresaRecicladora().getId());
        dto.setEmpresaNombre(historial.getEmpresaRecicladora().getNombreEmpresa());
        dto.setCantidadKg(historial.getCantidadKg());
        dto.setPuntosGanados(historial.getPuntosGanados());
        dto.setFechaReciclaje(historial.getFechaReciclaje());
        dto.setEstado(historial.getEstado());
        dto.setCo2Evitado(historial.getCo2Evitado());
        dto.setEnergiaAhorrada(historial.getEnergiaAhorrada());
        dto.setAguaConservada(historial.getAguaConservada());
        dto.setCreatedAt(historial.getCreatedAt());
        dto.setUpdatedAt(historial.getUpdatedAt());
        return dto;
    }
}