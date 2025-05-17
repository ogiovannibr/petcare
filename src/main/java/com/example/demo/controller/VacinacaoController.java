package com.example.demo.controller;

import com.example.demo.dto.VacinacaoDTO;
import com.example.demo.service.VacinacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vacinacoes")
public class VacinacaoController {
    @Autowired
    private VacinacaoService vacinacaoService;

    @GetMapping
    public List<VacinacaoDTO> findAll() {
        return vacinacaoService.findAll();
    }

    @GetMapping("/{id}")
    public VacinacaoDTO findById(@PathVariable Long id) {
        return vacinacaoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VacinacaoDTO vacinacaoDTO) {
        try {
            return ResponseEntity.ok(vacinacaoService.save(vacinacaoDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VacinacaoDTO vacinacaoDTO) {
        try {
            vacinacaoDTO.setId(id);
            return ResponseEntity.ok(vacinacaoService.save(vacinacaoDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vacinacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/animal/{animalId}")
    public List<VacinacaoDTO> findByAnimal(@PathVariable Long animalId) {
        return vacinacaoService.findByAnimal(animalId);
    }

    @GetMapping("/proximas-vencer")
    public List<VacinacaoDTO> findProximasVacinas(
            @RequestParam String inicio,
            @RequestParam String fim) {
        LocalDate dataInicio = LocalDate.parse(inicio);
        LocalDate dataFim = LocalDate.parse(fim);
        return vacinacaoService.findProximasVacinas(dataInicio, dataFim);
    }
    
    @GetMapping("/veterinario/{veterinarioId}")
    public List<VacinacaoDTO> findByVeterinario(@PathVariable Long veterinarioId) {
        return vacinacaoService.findByVeterinario(veterinarioId);
    }
}