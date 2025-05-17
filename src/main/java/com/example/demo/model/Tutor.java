package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Animal> animais;
}