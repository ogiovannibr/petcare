package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Vacinacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Animal animal;
    
    @ManyToOne
    private Veterinario veterinario;
    
    private String vacina;
    private String lote;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
}