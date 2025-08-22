package com.example.demo.service;

import com.example.demo.dto.UsuarioLogroCreationDTO;
import com.example.demo.dto.UsuarioLogroResponseDTO;
import com.example.demo.model.Logro;
import com.example.demo.model.UsuarioLogro;
import com.example.demo.model.UsuarioLogroId;
import com.example.demo.repository.LogroRepository;
import com.example.demo.repository.UsuarioLogroRepository;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioLogroServiceImpl implements UsuarioLogroService {

    private final UsuarioLogroRepository usuarioLogroRepository;
    private final UsuarioRepository usuarioRepository;
    private final LogroRepository logroRepository;

    public UsuarioLogroServiceImpl(UsuarioLogroRepository usuarioLogroRepository,
                                  UsuarioRepository usuarioRepository,
                                  LogroRepository logroRepository) {
        this.usuarioLogroRepository = usuarioLogroRepository;
        this.usuarioRepository = usuarioRepository;
        this.logroRepository = logroRepository;
    }

    @Override
    public UsuarioLogroResponseDTO save(UsuarioLogroCreationDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Logro logro = logroRepository.findById(dto.getLogroId())
                .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

        UsuarioLogroId id = new UsuarioLogroId(dto.getUsuarioId(), dto.getLogroId());
        UsuarioLogro usuarioLogro = new UsuarioLogro();
        usuarioLogro.setId(id);
        usuarioLogro.setUsuario(usuario);
        usuarioLogro.setLogro(logro);
        usuarioLogro.setFechaObtenido(dto.getFechaObtenido());

        UsuarioLogro savedUsuarioLogro = usuarioLogroRepository.save(usuarioLogro);
        return convertToDTO(savedUsuarioLogro);
    }

    @Override
    public Optional<UsuarioLogroResponseDTO> findById(UsuarioLogroId id) {
        return usuarioLogroRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<UsuarioLogroResponseDTO> findAll() {
        return usuarioLogroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioLogroResponseDTO update(UsuarioLogroId id, UsuarioLogroCreationDTO dto) {
        return usuarioLogroRepository.findById(id).map(usuarioLogro -> {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Logro logro = logroRepository.findById(dto.getLogroId())
                    .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

            usuarioLogro.setUsuario(usuario);
            usuarioLogro.setLogro(logro);
            usuarioLogro.setFechaObtenido(dto.getFechaObtenido());

            UsuarioLogro updatedUsuarioLogro = usuarioLogroRepository.save(usuarioLogro);
            return convertToDTO(updatedUsuarioLogro);
        }).orElseThrow(() -> new RuntimeException("UsuarioLogro no encontrado con ID " + id.getUsuarioId() + "-" + id.getLogroId()));
    }

    @Override
    public void deleteById(UsuarioLogroId id) {
        usuarioLogroRepository.deleteById(id);
    }

    private UsuarioLogroResponseDTO convertToDTO(UsuarioLogro usuarioLogro) {
        UsuarioLogroResponseDTO dto = new UsuarioLogroResponseDTO();
        dto.setUsuarioId(usuarioLogro.getId().getUsuarioId());
        dto.setLogroId(usuarioLogro.getId().getLogroId());
        dto.setFechaObtenido(usuarioLogro.getFechaObtenido());
        return dto;
    }
}