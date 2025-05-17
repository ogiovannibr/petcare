package com.example.demo.service;

import com.example.demo.dto.TutorDTO;
import com.example.demo.model.Tutor;
import com.example.demo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    public List<TutorDTO> findAll() {
        return tutorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TutorDTO findById(Long id) {
        return tutorRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    @Transactional
    public TutorDTO save(TutorDTO tutorDTO) {
        Tutor tutor = convertToEntity(tutorDTO);
        tutor = tutorRepository.save(tutor);
        return convertToDTO(tutor);
    }

    @Transactional
    public void delete(Long id) {
        tutorRepository.deleteById(id);
    }

    public TutorDTO findByCpf(String cpf) {
        return tutorRepository.findByCpf(cpf)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    private TutorDTO convertToDTO(Tutor tutor) {
        TutorDTO dto = new TutorDTO();
        dto.setId(tutor.getId());
        dto.setNome(tutor.getNome());
        dto.setCpf(tutor.getCpf());
        dto.setTelefone(tutor.getTelefone());
        dto.setEmail(tutor.getEmail());
        dto.setEndereco(tutor.getEndereco());
        dto.setCidade(tutor.getCidade());
        dto.setEstado(tutor.getEstado());
        dto.setCep(tutor.getCep());
        return dto;
    }

    private Tutor convertToEntity(TutorDTO dto) {
        Tutor tutor = new Tutor();
        if (dto.getId() != null) {
            tutor = tutorRepository.findById(dto.getId())
                    .orElse(new Tutor());
        }
        tutor.setNome(dto.getNome());
        tutor.setCpf(dto.getCpf());
        tutor.setTelefone(dto.getTelefone());
        tutor.setEmail(dto.getEmail());
        tutor.setEndereco(dto.getEndereco());
        tutor.setCidade(dto.getCidade());
        tutor.setEstado(dto.getEstado());
        tutor.setCep(dto.getCep());
        return tutor;
    }
}