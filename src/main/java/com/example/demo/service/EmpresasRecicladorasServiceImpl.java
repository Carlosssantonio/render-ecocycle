package com.example.demo.service;

import com.example.demo.dto.EmpresasRecicladorasCreationDTO;
import com.example.demo.dto.EmpresasRecicladorasResponseDTO;
import com.example.demo.model.EmpresasRecicladoras;
import com.example.demo.repository.EmpresasRecicladorasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresasRecicladorasServiceImpl implements EmpresasRecicladorasService {

    private final EmpresasRecicladorasRepository empresasRecicladorasRepository;

    public EmpresasRecicladorasServiceImpl(EmpresasRecicladorasRepository empresasRecicladorasRepository) {
        this.empresasRecicladorasRepository = empresasRecicladorasRepository;
    }

    @Override
    public EmpresasRecicladorasResponseDTO save(EmpresasRecicladorasCreationDTO dto) {
        EmpresasRecicladoras empresaRecicladora = new EmpresasRecicladoras();
        empresaRecicladora.setNombreEmpresa(dto.getNombreEmpresa());
        empresaRecicladora.setRut(dto.getRut());
        empresaRecicladora.setNombreContacto(dto.getNombreContacto());
        empresaRecicladora.setCargoContacto(dto.getCargoContacto());
        empresaRecicladora.setEmailCorporativo(dto.getEmailCorporativo());
        empresaRecicladora.setTelefono(dto.getTelefono());
        empresaRecicladora.setDireccion(dto.getDireccion());
        empresaRecicladora.setCapacidadMensual(dto.getCapacidadMensual());
        empresaRecicladora.setDescripcion(dto.getDescripcion());
        empresaRecicladora.setTerminosAceptados(dto.getTerminosAceptados());
        empresaRecicladora.setCalificacion(dto.getCalificacion());
        empresaRecicladora.setVerificado(dto.getVerificado());
        // fechaRegistro, createdAt, updatedAt are handled by @PrePersist

        EmpresasRecicladoras savedEmpresa = empresasRecicladorasRepository.save(empresaRecicladora);
        return convertToDTO(savedEmpresa);
    }

    @Override
    public Optional<EmpresasRecicladorasResponseDTO> findById(Long id) {
        return empresasRecicladorasRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<EmpresasRecicladorasResponseDTO> findAll() {
        return empresasRecicladorasRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpresasRecicladorasResponseDTO update(Long id, EmpresasRecicladorasCreationDTO dto) {
        return empresasRecicladorasRepository.findById(id).map(empresaRecicladora -> {
            empresaRecicladora.setNombreEmpresa(dto.getNombreEmpresa());
            empresaRecicladora.setRut(dto.getRut());
            empresaRecicladora.setNombreContacto(dto.getNombreContacto());
            empresaRecicladora.setCargoContacto(dto.getCargoContacto());
            empresaRecicladora.setEmailCorporativo(dto.getEmailCorporativo());
            empresaRecicladora.setTelefono(dto.getTelefono());
            empresaRecicladora.setDireccion(dto.getDireccion());
            empresaRecicladora.setCapacidadMensual(dto.getCapacidadMensual());
            empresaRecicladora.setDescripcion(dto.getDescripcion());
            empresaRecicladora.setTerminosAceptados(dto.getTerminosAceptados());
            empresaRecicladora.setCalificacion(dto.getCalificacion());
            empresaRecicladora.setVerificado(dto.getVerificado());
            // updatedAt is handled by @PreUpdate

            EmpresasRecicladoras updatedEmpresa = empresasRecicladorasRepository.save(empresaRecicladora);
            return convertToDTO(updatedEmpresa);
        }).orElseThrow(() -> new RuntimeException("Empresa Recicladora no encontrada con ID " + id));
    }

    @Override
    public void deleteById(Long id) {
        empresasRecicladorasRepository.deleteById(id);
    }

    private EmpresasRecicladorasResponseDTO convertToDTO(EmpresasRecicladoras empresaRecicladora) {
        EmpresasRecicladorasResponseDTO dto = new EmpresasRecicladorasResponseDTO();
        dto.setId(empresaRecicladora.getId());
        dto.setNombreEmpresa(empresaRecicladora.getNombreEmpresa());
        dto.setRut(empresaRecicladora.getRut());
        dto.setNombreContacto(empresaRecicladora.getNombreContacto());
        dto.setCargoContacto(empresaRecicladora.getCargoContacto());
        dto.setEmailCorporativo(empresaRecicladora.getEmailCorporativo());
        dto.setTelefono(empresaRecicladora.getTelefono());
        dto.setDireccion(empresaRecicladora.getDireccion());
        dto.setCapacidadMensual(empresaRecicladora.getCapacidadMensual());
        dto.setDescripcion(empresaRecicladora.getDescripcion());
        dto.setTerminosAceptados(empresaRecicladora.getTerminosAceptados());
        dto.setCalificacion(empresaRecicladora.getCalificacion());
        dto.setVerificado(empresaRecicladora.getVerificado());
        dto.setFechaRegistro(empresaRecicladora.getFechaRegistro());
        dto.setCreatedAt(empresaRecicladora.getCreatedAt());
        dto.setUpdatedAt(empresaRecicladora.getUpdatedAt());
        return dto;
    }
}