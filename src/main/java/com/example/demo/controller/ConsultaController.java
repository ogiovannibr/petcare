package com.example.demo.controller;

import com.example.demo.dto.ConsultaDTO;
import com.example.demo.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<ConsultaDTO> findAll() {
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public ConsultaDTO findById(@PathVariable Long id) {
        return consultaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ConsultaDTO consultaDTO) {
        try {
            if (!consultaService.isVeterinarioDisponivel(consultaDTO.getVeterinarioId(), consultaDTO.getDataHora())) {
                return ResponseEntity.badRequest().body("Veterinário não disponível neste horário");
            }
            return ResponseEntity.ok(consultaService.save(consultaDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        try {
            consultaDTO.setId(id);
            ConsultaDTO existingConsulta = consultaService.findById(id);
            
            // se a dataHora não mudou ou o veterinário esta disponível
            if (existingConsulta.getDataHora().equals(consultaDTO.getDataHora()) || 
                consultaService.isVeterinarioDisponivel(consultaDTO.getVeterinarioId(), consultaDTO.getDataHora())) {
                return ResponseEntity.ok(consultaService.save(consultaDTO));
            } else {
                return ResponseEntity.badRequest().body("Veterinário não disponível neste horário");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proximas")
    public List<ConsultaDTO> findProximasConsultas() {
        return consultaService.findProximasConsultas();
    }

    @GetMapping("/verificar-disponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @RequestParam Long veterinarioId,
            @RequestParam String dataHora) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dataHora);
            return ResponseEntity.ok(consultaService.isVeterinarioDisponivel(veterinarioId, dateTime));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
    
    @GetMapping("/animal/{animalId}")
    public List<ConsultaDTO> findByAnimal(@PathVariable Long animalId) {
        return consultaService.findByAnimal(animalId);
    }
    
    @GetMapping("/veterinario/{veterinarioId}")
    public List<ConsultaDTO> findByVeterinario(@PathVariable Long veterinarioId) {
        return consultaService.findByVeterinario(veterinarioId);
    }
}