package com.example.demo.service;

import com.example.demo.dto.ConsultaDTO;
import com.example.demo.model.Animal;
import com.example.demo.model.Consulta;
import com.example.demo.model.Veterinario;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.ConsultaRepository;
import com.example.demo.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<ConsultaDTO> findAll() {
        return consultaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ConsultaDTO findById(Long id) {
        return consultaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    @Transactional
    public ConsultaDTO save(ConsultaDTO consultaDTO) {
        Consulta consulta = convertToEntity(consultaDTO);
        
        // verifica se o vet. pode atender a espécie do animal
        Animal animal = consulta.getAnimal();
        Veterinario veterinario = consulta.getVeterinario();
        
        if (animal != null && veterinario != null && 
            !veterinario.getEspecializacoes().contains(animal.getEspecie())) {
            throw new RuntimeException("Veterinário não possui especialização para atender esta espécie");
        }
        
        consulta = consultaRepository.save(consulta);
        return convertToDTO(consulta);
    }

    @Transactional
    public void delete(Long id) {
        consultaRepository.deleteById(id);
    }

    public boolean isVeterinarioDisponivel(Long veterinarioId, LocalDateTime dataHora) {
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        
        // verificar se tem consulta no mesmo horário com margem de 30m
        LocalDateTime inicio = dataHora.minusMinutes(30);
        LocalDateTime fim = dataHora.plusMinutes(30);
        
        List<Consulta> consultasNoHorario = consultaRepository
                .findByVeterinarioAndDataHoraBetween(veterinario, inicio, fim);
        
        return consultasNoHorario.isEmpty();
    }

    public List<ConsultaDTO> findProximasConsultas() {
        return consultaRepository.findProximasConsultas().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ConsultaDTO> findByAnimal(Long animalId) {
        return consultaRepository.findByAnimalId(animalId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ConsultaDTO> findByVeterinario(Long veterinarioId) {
        return consultaRepository.findByVeterinarioId(veterinarioId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConsultaDTO convertToDTO(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(consulta.getId());
        dto.setLocal(consulta.getLocal());
        dto.setDataHora(consulta.getDataHora());
        dto.setObservacoes(consulta.getObservacoes());
        dto.setStatus(consulta.getStatus());
        
        if (consulta.getAnimal() != null) {
            dto.setAnimalId(consulta.getAnimal().getId());
            dto.setAnimalNome(consulta.getAnimal().getNome());
        }
        
        if (consulta.getVeterinario() != null) {
            dto.setVeterinarioId(consulta.getVeterinario().getId());
            dto.setVeterinarioNome(consulta.getVeterinario().getNome());
        }
        
        return dto;
    }

    private Consulta convertToEntity(ConsultaDTO dto) {
        Consulta consulta = new Consulta();
        if (dto.getId() != null) {
            consulta = consultaRepository.findById(dto.getId())
                    .orElse(new Consulta());
        }
        
        consulta.setLocal(dto.getLocal());
        consulta.setDataHora(dto.getDataHora());
        consulta.setObservacoes(dto.getObservacoes());
        consulta.setStatus(dto.getStatus());
        
        if (dto.getAnimalId() != null) {
            Animal animal = animalRepository.findById(dto.getAnimalId())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
            consulta.setAnimal(animal);
        }
        
        if (dto.getVeterinarioId() != null) {
            Veterinario veterinario = veterinarioRepository.findById(dto.getVeterinarioId())
                    .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
            consulta.setVeterinario(veterinario);
        }
        
        return consulta;
    }
}