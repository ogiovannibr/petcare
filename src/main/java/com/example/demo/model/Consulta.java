package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Consulta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Animal animal;
    
    @ManyToOne
    private Veterinario veterinario;
    
    private String local;
    private LocalDateTime dataHora;
    private String observacoes;
    
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
    
    public enum StatusConsulta {
        AGENDADA, REALIZADA, CANCELADA
    }
}