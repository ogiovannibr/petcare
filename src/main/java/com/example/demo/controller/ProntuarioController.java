package com.example.demo.controller;

import com.example.demo.dto.ProntuarioDTO;
import com.example.demo.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {
    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<ProntuarioDTO> findAll() {
        return prontuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ProntuarioDTO findById(@PathVariable Long id) {
        return prontuarioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProntuarioDTO prontuarioDTO) {
        try {
            return ResponseEntity.ok(prontuarioService.save(prontuarioDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProntuarioDTO prontuarioDTO) {
        try {
            prontuarioDTO.setId(id);
            return ResponseEntity.ok(prontuarioService.save(prontuarioDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prontuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<?> findByAnimal(@PathVariable Long animalId) {
        try {
            return ResponseEntity.ok(prontuarioService.findByAnimal(animalId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}