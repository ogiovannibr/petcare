package com.example.demo.controller;

import com.example.demo.dto.AnimalDTO;
import com.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<AnimalDTO> findAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public AnimalDTO findById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    @PostMapping
    public AnimalDTO create(@RequestBody AnimalDTO animalDTO) {
        return animalService.save(animalDTO);
    }

    @PutMapping("/{id}")
    public AnimalDTO update(@PathVariable Long id, @RequestBody AnimalDTO animalDTO) {
        animalDTO.setId(id);
        return animalService.save(animalDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tutor/{tutorId}")
    public List<AnimalDTO> findByTutor(@PathVariable Long tutorId) {
        return animalService.findByTutor(tutorId);
    }

    @GetMapping("/especie/{especie}")
    public List<AnimalDTO> findByEspecie(@PathVariable String especie) {
        return animalService.findByEspecie(especie);
    }
}