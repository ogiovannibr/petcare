package com.example.demo.service;

import com.example.demo.dto.VacinacaoDTO;
import com.example.demo.model.Animal;
import com.example.demo.model.Vacinacao;
import com.example.demo.model.Veterinario;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.VacinacaoRepository;
import com.example.demo.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacinacaoService {
    @Autowired
    private VacinacaoRepository vacinacaoRepository;
    
    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<VacinacaoDTO> findAll() {
        return vacinacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VacinacaoDTO findById(Long id) {
        return vacinacaoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Vacinação não encontrada"));
    }

    @Transactional
    public VacinacaoDTO save(VacinacaoDTO vacinacaoDTO) {
        Vacinacao vacinacao = convertToEntity(vacinacaoDTO);
        vacinacao = vacinacaoRepository.save(vacinacao);
        return convertToDTO(vacinacao);
    }

    @Transactional
    public void delete(Long id) {
        vacinacaoRepository.deleteById(id);
    }

    public List<VacinacaoDTO> findByAnimal(Long animalId) {
        return vacinacaoRepository.findByAnimalId(animalId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<VacinacaoDTO> findByVeterinario(Long veterinarioId) {
        return vacinacaoRepository.findByVeterinarioId(veterinarioId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VacinacaoDTO> findProximasVacinas(LocalDate inicio, LocalDate fim) {
        return vacinacaoRepository.findProximasVacinas(inicio, fim).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private VacinacaoDTO convertToDTO(Vacinacao vacinacao) {
        VacinacaoDTO dto = new VacinacaoDTO();
        dto.setId(vacinacao.getId());
        dto.setVacina(vacinacao.getVacina());
        dto.setLote(vacinacao.getLote());
        dto.setDataAplicacao(vacinacao.getDataAplicacao());
        dto.setDataProximaDose(vacinacao.getDataProximaDose());
        
        if (vacinacao.getAnimal() != null) {
            dto.setAnimalId(vacinacao.getAnimal().getId());
            dto.setAnimalNome(vacinacao.getAnimal().getNome());
        }
        
        if (vacinacao.getVeterinario() != null) {
            dto.setVeterinarioId(vacinacao.getVeterinario().getId());
            dto.setVeterinarioNome(vacinacao.getVeterinario().getNome());
        }
        
        return dto;
    }

    private Vacinacao convertToEntity(VacinacaoDTO dto) {
        Vacinacao vacinacao = new Vacinacao();
        if (dto.getId() != null) {
            vacinacao = vacinacaoRepository.findById(dto.getId())
                    .orElse(new Vacinacao());
        }
        
        vacinacao.setVacina(dto.getVacina());
        vacinacao.setLote(dto.getLote());
        vacinacao.setDataAplicacao(dto.getDataAplicacao());
        vacinacao.setDataProximaDose(dto.getDataProximaDose());
        
        if (dto.getAnimalId() != null) {
            Animal animal = animalRepository.findById(dto.getAnimalId())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
            vacinacao.setAnimal(animal);
        }
        
        if (dto.getVeterinarioId() != null) {
            Veterinario veterinario = veterinarioRepository.findById(dto.getVeterinarioId())
                    .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
            vacinacao.setVeterinario(veterinario);
        }
        
        return vacinacao;
    }
}