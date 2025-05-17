package com.example.demo.service;

import com.example.demo.dto.AnimalDTO;
import com.example.demo.model.Animal;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private TutorRepository tutorRepository;

    public List<AnimalDTO> findAll() {
        return animalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AnimalDTO findById(Long id) {
        return animalRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    @Transactional
    public AnimalDTO save(AnimalDTO animalDTO) {
        Animal animal = convertToEntity(animalDTO);
        animal = animalRepository.save(animal);
        return convertToDTO(animal);
    }

    @Transactional
    public void delete(Long id) {
        animalRepository.deleteById(id);
    }

    public List<AnimalDTO> findByTutor(Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        return animalRepository.findByTutor(tutor).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> findByEspecie(String especie) {
        return animalRepository.findByEspecie(especie).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AnimalDTO convertToDTO(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setId(animal.getId());
        dto.setNome(animal.getNome());
        dto.setEspecie(animal.getEspecie());
        dto.setRaca(animal.getRaca());
        dto.setDataNascimento(animal.getDataNascimento());
        if (animal.getTutor() != null) {
            dto.setTutorId(animal.getTutor().getId());
            dto.setTutorNome(animal.getTutor().getNome());
        }
        return dto;
    }

    private Animal convertToEntity(AnimalDTO dto) {
        Animal animal = new Animal();
        if (dto.getId() != null) {
            animal = animalRepository.findById(dto.getId())
                    .orElse(new Animal());
        }
        animal.setNome(dto.getNome());
        animal.setEspecie(dto.getEspecie());
        animal.setRaca(dto.getRaca());
        animal.setDataNascimento(dto.getDataNascimento());
        
        if (dto.getTutorId() != null) {
            Tutor tutor = tutorRepository.findById(dto.getTutorId())
                    .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
            animal.setTutor(tutor);
        }
        
        return animal;
    }
}