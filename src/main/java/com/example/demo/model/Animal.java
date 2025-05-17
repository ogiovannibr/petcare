package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    
    @ManyToOne
    private Tutor tutor;
    
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
    
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Vacinacao> vacinacoes;
    
    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL)
    private Prontuario prontuario;
}