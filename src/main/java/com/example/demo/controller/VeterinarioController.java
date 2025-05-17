package com.example.demo.controller;

import com.example.demo.dto.VeterinarioDTO;
import com.example.demo.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public List<VeterinarioDTO> findAll() {
        return veterinarioService.findAll();
    }

    @GetMapping("/{id}")
    public VeterinarioDTO findById(@PathVariable Long id) {
        return veterinarioService.findById(id);
    }

    @PostMapping
    public VeterinarioDTO create(@RequestBody VeterinarioDTO veterinarioDTO) {
        return veterinarioService.save(veterinarioDTO);
    }

    @PutMapping("/{id}")
    public VeterinarioDTO update(@PathVariable Long id, @RequestBody VeterinarioDTO veterinarioDTO) {
        veterinarioDTO.setId(id);
        return veterinarioService.save(veterinarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veterinarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especializacao/{especializacao}")
    public List<VeterinarioDTO> findByEspecializacao(@PathVariable String especializacao) {
        return veterinarioService.findByEspecializacao(especializacao);
    }
    
    @GetMapping("/crmv/{crmv}")
    public ResponseEntity<VeterinarioDTO> findByCrmv(@PathVariable String crmv) {
        try {
            return ResponseEntity.ok(veterinarioService.findByCrmv(crmv));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}