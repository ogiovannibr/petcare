package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Prontuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Animal animal;
    
    private String historico;
    private LocalDateTime ultimaAtualizacao;
    
    @PrePersist
    @PreUpdate
    public void atualizarData() {
        ultimaAtualizacao = LocalDateTime.now();
    }
}