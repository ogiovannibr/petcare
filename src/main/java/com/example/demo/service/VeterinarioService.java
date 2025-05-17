package com.example.demo.service;

import com.example.demo.dto.VeterinarioDTO;
import com.example.demo.model.Veterinario;
import com.example.demo.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<VeterinarioDTO> findAll() {
        return veterinarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VeterinarioDTO findById(Long id) {
        return veterinarioRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
    }

    @Transactional
    public VeterinarioDTO save(VeterinarioDTO veterinarioDTO) {
        Veterinario veterinario = convertToEntity(veterinarioDTO);
        veterinario = veterinarioRepository.save(veterinario);
        return convertToDTO(veterinario);
    }

    @Transactional
    public void delete(Long id) {
        veterinarioRepository.deleteById(id);
    }

    public List<VeterinarioDTO> findByEspecializacao(String especializacao) {
        return veterinarioRepository.findByEspecializacao(especializacao).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public VeterinarioDTO findByCrmv(String crmv) {
        return veterinarioRepository.findByCrmv(crmv)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
    }

    private VeterinarioDTO convertToDTO(Veterinario veterinario) {
        VeterinarioDTO dto = new VeterinarioDTO();
        dto.setId(veterinario.getId());
        dto.setNome(veterinario.getNome());
        dto.setCrmv(veterinario.getCrmv());
        dto.setTelefone(veterinario.getTelefone());
        dto.setEmail(veterinario.getEmail());
        dto.setEspecializacoes(veterinario.getEspecializacoes());
        return dto;
    }

    private Veterinario convertToEntity(VeterinarioDTO dto) {
        Veterinario veterinario = new Veterinario();
        if (dto.getId() != null) {
            veterinario = veterinarioRepository.findById(dto.getId())
                    .orElse(new Veterinario());
        }
        veterinario.setNome(dto.getNome());
        veterinario.setCrmv(dto.getCrmv());
        veterinario.setTelefone(dto.getTelefone());
        veterinario.setEmail(dto.getEmail());
        veterinario.setEspecializacoes(dto.getEspecializacoes());
        return veterinario;
    }
}