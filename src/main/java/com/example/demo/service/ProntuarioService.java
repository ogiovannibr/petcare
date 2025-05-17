package com.example.demo.service;

import com.example.demo.dto.ProntuarioDTO;
import com.example.demo.model.Animal;
import com.example.demo.model.Prontuario;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {
    @Autowired
    private ProntuarioRepository prontuarioRepository;
    
    @Autowired
    private AnimalRepository animalRepository;

    public List<ProntuarioDTO> findAll() {
        return prontuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProntuarioDTO findById(Long id) {
        return prontuarioRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
    }

    @Transactional
    public ProntuarioDTO save(ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = convertToEntity(prontuarioDTO);
        prontuario.setUltimaAtualizacao(LocalDateTime.now());
        prontuario = prontuarioRepository.save(prontuario);
        return convertToDTO(prontuario);
    }

    @Transactional
    public void delete(Long id) {
        prontuarioRepository.deleteById(id);
    }

    public ProntuarioDTO findByAnimal(Long animalId) {
        return prontuarioRepository.findByAnimalId(animalId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
    }

    private ProntuarioDTO convertToDTO(Prontuario prontuario) {
        ProntuarioDTO dto = new ProntuarioDTO();
        dto.setId(prontuario.getId());
        dto.setHistorico(prontuario.getHistorico());
        dto.setUltimaAtualizacao(prontuario.getUltimaAtualizacao());
        
        if (prontuario.getAnimal() != null) {
            dto.setAnimalId(prontuario.getAnimal().getId());
            dto.setAnimalNome(prontuario.getAnimal().getNome());
        }
        
        return dto;
    }

    private Prontuario convertToEntity(ProntuarioDTO dto) {
        Prontuario prontuario = new Prontuario();
        if (dto.getId() != null) {
            prontuario = prontuarioRepository.findById(dto.getId())
                    .orElse(new Prontuario());
        }
        
        prontuario.setHistorico(dto.getHistorico());
        
        if (dto.getAnimalId() != null) {
            Animal animal = animalRepository.findById(dto.getAnimalId())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
            prontuario.setAnimal(animal);
        }
        
        return prontuario;
    }
}