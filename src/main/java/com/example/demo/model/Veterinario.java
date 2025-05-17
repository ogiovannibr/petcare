package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Veterinario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crmv;
    private String telefone;
    private String email;
    
    @ElementCollection
    private List<String> especializacoes;
    
    @OneToMany(mappedBy = "veterinario")
    private List<Consulta> consultas;
    
    @OneToMany(mappedBy = "veterinario")
    private List<Vacinacao> vacinacoes;
}