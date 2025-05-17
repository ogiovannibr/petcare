package com.example.demo.controller;

import com.example.demo.dto.TutorDTO;
import com.example.demo.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @GetMapping
    public List<TutorDTO> findAll() {
        return tutorService.findAll();
    }

    @GetMapping("/{id}")
    public TutorDTO findById(@PathVariable Long id) {
        return tutorService.findById(id);
    }

    @PostMapping
    public TutorDTO create(@RequestBody TutorDTO tutorDTO) {
        return tutorService.save(tutorDTO);
    }

    @PutMapping("/{id}")
    public TutorDTO update(@PathVariable Long id, @RequestBody TutorDTO tutorDTO) {
        tutorDTO.setId(id);
        return tutorService.save(tutorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tutorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public TutorDTO findByCpf(@PathVariable String cpf) {
        return tutorService.findByCpf(cpf);
    }
}